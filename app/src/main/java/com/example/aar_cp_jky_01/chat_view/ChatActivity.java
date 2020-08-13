package com.example.aar_cp_jky_01.chat_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aar_cp_jky_01.R;
import com.example.aar_cp_jky_01.adapter.ChatAdapter;
import com.example.aar_cp_jky_01.adapter.QuestionListAdapter;
import com.example.aar_cp_jky_01.application.App;
import com.example.aar_cp_jky_01.base.BaseMvpActivity;
import com.example.aar_cp_jky_01.base.BasePresenter;
import com.example.aar_cp_jky_01.bean.CheckMoreBean;
import com.example.aar_cp_jky_01.bean.CreateUserBean;
import com.example.aar_cp_jky_01.bean.DiseaseMessageBean;
import com.example.aar_cp_jky_01.bean.HistoryBean;
import com.example.aar_cp_jky_01.bean.IssueBean;
import com.example.aar_cp_jky_01.bean.IssueContentBean;
import com.example.aar_cp_jky_01.bean.MessageBean;
import com.example.aar_cp_jky_01.bean.WelcomeBean;
import com.example.aar_cp_jky_01.common.Comm;
import com.example.aar_cp_jky_01.contract.IChatContract;
import com.example.aar_cp_jky_01.custom_view.PageIndicatorView;
import com.example.aar_cp_jky_01.http.LoginHttp;
import com.example.aar_cp_jky_01.page_scroll.HorizontalPageLayoutManager;
import com.example.aar_cp_jky_01.page_scroll.PagingItemDecoration;
import com.example.aar_cp_jky_01.page_scroll.PagingScrollHelper;
import com.example.aar_cp_jky_01.presenter.ChatPresenter;
import com.example.aar_cp_jky_01.utils.ScreenUtil;
import com.example.aar_cp_jky_01.utils.SharePreUtils;
import com.example.aar_cp_jky_01.utils.SystemUtils;
import com.example.aar_cp_jky_01.voiceSet.VoiceSetting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import library.PullToRefreshBase;
import library.PullToRefreshListView;

public class ChatActivity extends BaseMvpActivity<IChatContract.IChatModel, IChatContract.ChatPresenter> implements IChatContract.IChatView, PagingScrollHelper.onPageChangeListener {


    public static ChatActivity chatActivity;

    @BindView(R.id.iv_back)
    ImageView tv_back;// 返回按钮
    @BindView(R.id.tv_title)
    TextView tv_title;// 标题
    @BindView(R.id.iv_voice)
    ImageView iv_voice;// 声音开关
    @BindView(R.id.iv_speek)
    ImageView iv_speek;// 语音按钮
    @BindView(R.id.tv_more)
    TextView tv_more;// 加号
    @BindView(R.id.tv_send)
    TextView tv_send;// 发送按钮
    @BindView(R.id.et_input)
    EditText et_input;// 输入框
    @BindView(R.id.prlv_listview)
    PullToRefreshListView prlv_listview;// 对话列表
    @BindView(R.id.ll_btn)
    LinearLayout ll_btn;// 确认，取消布局
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;// 取消按钮
    @BindView(R.id.tv_sure)
    TextView tv_sure;// 确认按钮
    @BindView(R.id.ll_more)
    LinearLayout ll_more;// 横向展示布局
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;// 底部导航栏按钮
    @BindView(R.id.chat_sound_layout)
    ConstraintLayout chat_sound_layout;// 语音布局
    @BindView(R.id.cl_jia_more)
    ConstraintLayout cl_jia_more;// 加号内布局
    @BindView(R.id.cl_base)
    ConstraintLayout cl_base;// base布局

    ListView mListView;

    // 接过来的UserId
    private String userId;
    private SpeechRecognizer mIat;
    private VoiceSetting voiceSetting;
    private ChatAdapter chatAdapter;

    public static String phoneType = "1";
    private String send_code = "";

    // 文本 = 0，按钮 = 1，多选数据按钮 = 2
    public static final int TEXT = 0, BTN = 1, CHECK_MORE = 2;
    // 发送中 = 1000，发送成功 = 2000，发送失败 = 3000
    public static final int sending = 1000, send_success = 2000, send_failed = 3000;
    private List<CheckMoreBean> list;// 多选按钮数据
    private QuestionListAdapter questionListAdapter;// 横向选病适配器

    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition = 0;// 标记上次滑动位置

    private int totalHeight;

    @Override
    protected void init() {
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @OnClick({R.id.tv_send, R.id.tv_more, R.id.et_input, R.id.iv_speek, R.id.iv_voice, R.id.iv_back, R.id.tv_sure, R.id.tv_cancel})
    @Override
    public void widgetClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_send:
                Log.d(TAG, "点击了发送");
                String str = et_input.getText().toString().trim();
                if (TextUtils.isEmpty(str)) return;
                sendMessages(str);
                et_input.setText("");
                mListView.setSelection(mListView.getAdapter().getCount() - 1);
                break;
            case R.id.tv_more:
                Log.d(TAG, "点击了加号");
                setting(true, true, false);
                break;
            case R.id.et_input:
                Log.d(TAG, "点击了输入框");
                setting(false, true, true);
                mListView.setSelection(mListView.getAdapter().getCount() - 1);
                break;
            case R.id.iv_speek:
                Log.d(TAG, "点击了语音话筒");
                setting(true, false, true);
                break;
            case R.id.iv_voice:
                Log.d(TAG, "点击了音量开关");
                boolean flag = SharePreUtils.getSoundsBoolean(chatActivity);
                SharePreUtils.saveSoundsBoolean(chatActivity, !flag);
                iv_voice.setSelected(!flag);
                if (flag && chatAdapter != null) {
                    chatAdapter.stopRecoard();
                }
                break;
            case R.id.iv_back:
                Log.d(TAG, "点击了返回");
                break;
            case R.id.tv_sure:
                String str_ = "";
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isCheck()) {
                            if (!TextUtils.isEmpty(str_)) {
                                str_ += ",";
                            }
                            str_ += list.get(i).getMessage();
                        }
                    }
                    showSureCancle(false);
                    et_input.setText("");
                    if (TextUtils.isEmpty(str_)) {
                        sendMessages("没有");
                    } else {
                        sendMessages(str_);
                    }
                    ll_more.removeAllViews();
                    if (ll_more.getVisibility() == View.VISIBLE) {
                        ll_more.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.tv_cancel:
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIsCheck(false);
                    }
                    questionListAdapter.setList(list);
                    showSureCancle(false);
                }
                break;
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void initParms(Bundle parms) {
        if (parms != null) {
            userId = parms.getString("userId") == null ? "changhao" : parms.getString("userId");
        } else {
            userId = "";
        }
    }

    @Override
    public void initView(Intent intent) {
        chatActivity = this;
    }

    @Override
    protected void initData() {
        super.initData();
        controlKeyboardLayout(cl_base, et_input);
        tv_title.setText(Comm.name);// 设置标题名字
        iv_voice.setSelected(SharePreUtils.getSoundsBoolean(chatActivity));// 设置图片标识
        // 初始化讯飞引擎
        initVoice();
        // 对话列表
        chatAdapter = new ChatAdapter(chatActivity);
        mListView = prlv_listview.getRefreshableView();
        mListView.setAdapter(chatAdapter);
        // 创建客户
        getCreateUser(userId);
        // 请求历史
        getHistory(LoginHttp.getLoginHttp().getLoginId(), Comm.message_id);
        // 请求欢迎语
        getLoadWelcomeMesgs(LoginHttp.getLoginHttp().getLoginId());
    }

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域的底部
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }


    private void initVoice() {
        // 初始化讯飞语音
        SpeechUtility utility = SpeechUtility.createUtility(chatActivity, SpeechConstant.APPID + "=5a0007f1");
        //使用SpeechRecognizer对象，可根据回调消息自定义界面；
        if (mIat == null) {
            mIat = SpeechRecognizer.createRecognizer(chatActivity, new InitListener() {
                @Override
                public void onInit(int i) {
                    if (i != ErrorCode.SUCCESS) {
                        Log.e(TAG, "初始化失败，错误码：" + i);
                    }
                }
            });
            voiceSetting = new VoiceSetting(mIat);
//            voiceSetting.VoiceSetting(TAG, chatActivity);
        }
    }

    /**
     * 请求问题结果
     *
     * @param issue
     */
    private void getResult(String issue, String send_code) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "ctk");
        params.put("message_type", "5");//
        params.put("content", issue);
        params.put("c_id", LoginHttp.getLoginHttp().getLoginId());
        if (send_code != null) {
            params.put("message_id", send_code);
        } else {
            params.put("message_id", "");
        }
        if (!TextUtils.isEmpty(phoneType)) {
            params.put("phoneType", phoneType);
        }
        presenter.getIssue(params);
    }


    /**
     * 请求欢迎语
     *
     * @param loginId
     */
    private void getLoadWelcomeMesgs(String loginId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "XiaoYi_chat_his_pb");
        params.put("APPKEY", Comm.KEY);
        presenter.getWelcome(params);
    }

    /**
     * 请求历史
     *
     * @param loginId
     */
    private void getHistory(String loginId, String message_id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryLishiBycid");
        params.put("c_id", loginId);
        params.put("limit", "5");
        params.put("message_id", message_id);
        presenter.getHistory(params);
    }

    /**
     * 创建客户请求
     *
     * @param userId
     */
    private void getCreateUser(String userId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "createAppUsr");
        params.put("merchant_id", Comm.MERCHANT_ID);
        params.put("site_id", Comm.site_id);
        params.put("customername", Comm.name);
        presenter.getCreateUser(params);
    }


    @Override
    public void setListener() {
        prlv_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 下拉刷新
                getHistory(LoginHttp.getLoginHttp().getLoginId(), Comm.message_id);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        //listView 滚动监听
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                        scrollFlag = false;
                        // 判断滚动到底部
                        if (mListView.getLastVisiblePosition() == (mListView
                                .getCount() - 1)) {

                        }
                        // 判断滚动到顶部
                        if (mListView.getFirstVisiblePosition() == 0) {

                        }

                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        totalHeight = 0;
                        for (int k = 0; k < chatAdapter.getCount(); k++) {//通过其适配器获取总高度
                            View mView = chatAdapter.getView(k, null, mListView);
                            mView.measure(
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                            totalHeight += mView.getMeasuredHeight();
                        }
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                        scrollFlag = false;
                        break;
                }
            }


            /**
             * firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
             * visibleItemCount：当前能看见的列表项个数（小半个也算） totalItemCount：列表项共数
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // 当开始滑动且ListView底部的Y轴点超出屏幕最大范围时，显示或隐藏顶部按钮
                if (scrollFlag && totalHeight >= ScreenUtil.getScreenHeight(App.getContext())) {
                    if (firstVisibleItem > lastVisibleItemPosition) {// 上滑
                    } else if (firstVisibleItem < lastVisibleItemPosition) {// 下滑
                    } else {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }
        });

        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        SystemUtils.hideSoftBord(ChatActivity.this, et_input);
                        setting(true, true, true);
                }
                return false;
            }
        });
        chatAdapter.setSetSureOrCancle(new ChatAdapter.SetSureOrCancle() {
            @Override
            public void setSureOrCancleListener(String btn_text, boolean isShow) {
                sendMessages(btn_text);
                showSureCancle(isShow);
            }
        });
        chatAdapter.setSetDiseaseMessage(new ChatAdapter.SetDiseaseMessage() {
            @Override
            public void setDiseaseMessageListener(List<CheckMoreBean> checkMoreBeans) {
                setMore(checkMoreBeans);
                mListView.setSelection(mListView.getAdapter().getCount() - 1);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public BasePresenter initPresenter() {
        return new ChatPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 发送文本消息
     *
     * @param str
     */
    public void sendMessages(String str) {
        MessageBean messageBean = new MessageBean();
        messageBean.setMessageType(TEXT);
        messageBean.setCotent_text(str);
        messageBean.setMeSend(true);
        chatAdapter.setMessage(messageBean);
        getResult(str, send_code);
    }

    /**
     * 解析数据
     */
    private void analysisMsg(Object obj) {
        List<IssueContentBean> issueList;
        MessageBean messageBean = new MessageBean();
        messageBean.setMeSend(false);
        if (obj instanceof String) {
            messageBean.setMessageType(TEXT);
            messageBean.setCotent_text((String) obj);
            chatAdapter.setMessage(messageBean);
        } else if (obj instanceof IssueBean) {
            phoneType = "";
            IssueBean issueBean = (IssueBean) obj;
            send_code = issueBean.getMessage_id();
            if (issueBean.getContent().contains("btns")) {
                messageBean.setMessageType(BTN);
                issueList = new Gson().fromJson(issueBean.getContent(), new TypeToken<List<IssueContentBean>>() {
                }.getType());
                for (int i = 0; i < issueList.size(); i++) {
                    IssueContentBean issueContentBean = issueList.get(i);
                    messageBean.setCotent_text(issueContentBean.getCont());
                    messageBean.setBtns(issueContentBean.getBtns());
                }
                chatAdapter.setMessage(messageBean);
            } else if (issueBean.getContent().contains("situations")) {
                if (ll_more.getVisibility() == View.GONE) {
                    ll_more.setVisibility(View.VISIBLE);
                }
                List<DiseaseMessageBean> diseaseMessageBeans = new Gson().fromJson(issueBean.getContent(), new TypeToken<List<DiseaseMessageBean>>() {
                }.getType());
                for (int i = 0; i < diseaseMessageBeans.size(); i++) {
                    List<CheckMoreBean> checkMoreBeans = new ArrayList<>();
                    List<String> situations = diseaseMessageBeans.get(i).getSituations();
                    for (int i1 = 0; i1 < situations.size(); i1++) {
                        checkMoreBeans.add(CheckMoreBean.addInfo(situations.get(i1), "1", ""));
                    }
                    messageBean.setMessageType(CHECK_MORE);
                    messageBean.setCotent_text(diseaseMessageBeans.get(i).getCont());
                    messageBean.setCheckMoreBeans(checkMoreBeans);
                    chatAdapter.setMessage(messageBean);
                }
            }
        }
        mListView.setSelection(mListView.getAdapter().getCount() - 1);
    }

    /**
     * 选病症横向分页
     *
     * @param list
     */
    public void setMore(final List<CheckMoreBean> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(i).setIsSelect(0);
            } else {
                list.get(i).setIsSelect(1);
            }
        }
        if (ll_more.getChildCount() != 0) {
            return;
        }
        RecyclerView rv_question_list;
        PageIndicatorView page_indicator;
        HorizontalPageLayoutManager horizontalPageLayoutManager = null;
        PagingItemDecoration pagingItemDecoration = null;
        RecyclerView.LayoutManager layoutManager = null;
        RecyclerView.ItemDecoration itemDecoration = null;
        PagingScrollHelper scrollHelper = new PagingScrollHelper();
        View chGroup;
        chGroup = View.inflate(this, R.layout.item_somecheck, null);
        ll_more.addView(chGroup);
        // 获取列表控件
        rv_question_list = chGroup.findViewById(R.id.rv_question_list);
        // 获取底部指针控件
        page_indicator = chGroup.findViewById(R.id.page_indicator);
        // 设置列表布局
        horizontalPageLayoutManager = new HorizontalPageLayoutManager(3, 3);
        pagingItemDecoration = new PagingItemDecoration(this, horizontalPageLayoutManager);
        layoutManager = horizontalPageLayoutManager;
        itemDecoration = pagingItemDecoration;
        rv_question_list.setLayoutManager(layoutManager);
        // 初始化适配器
        questionListAdapter = new QuestionListAdapter(ChatActivity.this);
        rv_question_list.setAdapter(questionListAdapter);
        questionListAdapter.setList(list);
        scrollHelper.setUpRecycleView(rv_question_list);
        scrollHelper.setOnPageChangeListener(this);
        rv_question_list.setHorizontalScrollBarEnabled(true);
        //获取总页数,采用这种方法才能获得正确的页数。否则会因为RecyclerView.State 缓存问题，页数不正确。
        rv_question_list.post(new Runnable() {
            @Override
            public void run() {
                //tv_page_total.setText("共" + scrollHelper.getPageCount() + "页");
                int pageCount = scrollHelper.getPageCount();
                // 设置指针监听
                page_indicator.initIndicator(pageCount);
            }
        });
        questionListAdapter.setHideSound(new QuestionListAdapter.OnCheckbox_hideSound() {
            @Override
            public void onCheckbox_hideSound() {
                setting(true, true, true);
            }
        });
        questionListAdapter.setOnCheckboxItem(new QuestionListAdapter.OnCheckboxItem() {
            @Override
            public void onCheckboxItem(boolean isOpen, String message) {
                if (isOpen) {
                    showSureCancle(true);
                } else {
                    showSureCancle(false);
                }
            }
        });
    }

    /**
     * 横向列表底部小点坐标
     *
     * @param index
     */
    @Override
    public void onPageChange(int index) {

    }

    /**
     * 是否显示确定取消按钮
     *
     * @param isShow
     */
    public void showSureCancle(Boolean isShow) {
        // 是否显示确定取消按钮
        if (ll_btn == null)
            return;
        if (isShow) {
            if (ll_btn.getVisibility() != View.VISIBLE) {
                ll_btn.setVisibility(View.VISIBLE);
            }
        } else {
            if (ll_btn.getVisibility() != View.GONE) {
                ll_btn.setVisibility(View.GONE);
            }
        }
    }


    /**
     * 设置
     *
     * @param hideInput 键盘
     * @param isSound   语音布局
     * @param hideMore  加号布局
     */
    private void setting(boolean hideInput, boolean isSound, boolean hideMore) {
        if (hideInput) {
            // 隐藏键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View v = getWindow().peekDecorView();
            if (null != v) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }

        if (isSound) {
            // 隐藏语音
            if (chat_sound_layout == null) {
                chat_sound_layout = (ConstraintLayout) findViewById(R.id.chat_sound_layout);
            }
            if (chat_sound_layout.getVisibility() != View.GONE)
                chat_sound_layout.setVisibility(View.GONE);
        } else {
            // 显示语音
            mListView.setSelection(mListView.getAdapter().getCount() - 1);
            if (chat_sound_layout == null) {
                chat_sound_layout = (ConstraintLayout) findViewById(R.id.chat_sound_layout);
            }
            if (chat_sound_layout.getVisibility() == View.GONE)
                chat_sound_layout.setVisibility(View.VISIBLE);
        }

        if (hideMore) {
            // 隐藏加号更多
            if (cl_jia_more == null) {
                cl_jia_more = findViewById(R.id.cl_jia_more);
            }
            if (cl_jia_more.getVisibility() != View.GONE) {
                cl_jia_more.setVisibility(View.GONE);
            }
        } else {
            // 显示加号更多
            mListView.setSelection(mListView.getAdapter().getCount() - 1);
            if (cl_jia_more == null) {
                cl_jia_more = findViewById(R.id.cl_jia_more);
            }
            if (cl_jia_more.getVisibility() == View.GONE) {
                cl_jia_more.setVisibility(View.VISIBLE);
            }
        }
        if (chat_sound_layout.getVisibility() == View.GONE) {
            iv_speek.setImageResource(R.mipmap.speek_cpjky);
        } else {
            iv_speek.setImageResource(R.mipmap.text_input);
        }
    }


    @Override
    public void failure(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * 客户创建成功回调
     *
     * @param result
     */
    @Override
    public void onCreateUserSuccess(Object result) {
        if (result != null) {
            Log.d(TAG, result.toString());
            if (result instanceof CreateUserBean) {
                if (((CreateUserBean) result).getCode().equals("0")) {
                    Log.d(TAG, ((CreateUserBean) result).getMessage());
                    // 保存客户Id
                    LoginHttp.getLoginHttp().setLoginId(((CreateUserBean) result).getServer_params().getCustomer_id());
                    Log.d(TAG, "Customer_id:" + ((CreateUserBean) result).getServer_params().getCustomer_id());
                }
            }
        }
    }

    /**
     * 客户创建失败回调
     *
     * @param error
     */
    @Override
    public void onCreateUserFailed(Object error) {
        Log.e(TAG, (String) error);
    }

    /**
     * 请求历史成功回调
     *
     * @param result
     */
    @Override
    public void onHistorySuccess(Object result) {
        if (result != null) {
            prlv_listview.onRefreshComplete();
            if (result instanceof HistoryBean) {
                if (((HistoryBean) result).getCode().equals("0")) {
                    Log.d(TAG, ((HistoryBean) result).getMessage());
                }
            }
        }
    }

    /**
     * 请求历史失败回调
     *
     * @param error
     */
    @Override
    public void onHistoryFailed(Object error) {
        Log.e(TAG, (String) error);
        prlv_listview.onRefreshComplete();
    }

    /**
     * 查询欢迎语成功回调
     *
     * @param result
     */
    @Override
    public void onWelcomeSuccess(Object result) {
        if (result != null) {
            if (result instanceof WelcomeBean) {
                if (((WelcomeBean) result).getCode().equals("0")) {
                    Log.d(TAG, ((WelcomeBean) result).getMessage());
                    String str = ((WelcomeBean) result).getServer_params();
                    analysisMsg(str);
                }
            }
        }
    }

    /**
     * 查询欢迎语失败回调
     *
     * @param error
     */
    @Override
    public void onWelcomeFailed(Object error) {
        Log.e(TAG, (String) error);
    }

    /**
     * 查询问题结果成功回调
     *
     * @param result
     */
    @Override
    public void onIssueSuccess(Object result) {
        if (result != null) {
            if (result instanceof IssueBean) {
                Log.d(TAG, "查询问题结果：" + ((IssueBean) result).getContent());
                analysisMsg(result);
            }
        }
    }

    /**
     * 查询问题结果失败回调
     *
     * @param error
     */
    @Override
    public void onIssueFailed(Object error) {
        Log.e(TAG, (String) error);
    }


}