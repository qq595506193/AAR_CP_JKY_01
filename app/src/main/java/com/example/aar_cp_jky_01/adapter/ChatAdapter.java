package com.example.aar_cp_jky_01.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aar_cp_jky_01.R;
import com.example.aar_cp_jky_01.bean.CheckMoreBean;
import com.example.aar_cp_jky_01.bean.IssueContentBean;
import com.example.aar_cp_jky_01.bean.MessageBean;
import com.example.aar_cp_jky_01.chat_view.ChatActivity;
import com.example.aar_cp_jky_01.utils.SharePreUtils;
import com.example.aar_cp_jky_01.utils.SpeechUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List<MessageBean> lists;
    private SpeechUtils mSpeechUtils;
    private boolean isOpenSound = false;
    boolean isReceive = false;

    public ChatAdapter(Context context) {
        super();
        this.context = context;
        lists = new ArrayList<>();
        isOpenSound = SharePreUtils.getSoundsBoolean(context);
//        mSpeechUtils = new SpeechUtils((Activity) context);
    }

    public void setMessage(MessageBean message) {
        if (message != null) {
            isReceive = true;
            lists.add(message);
        }
        notifyDataSetChanged();
    }

    /**
     * 停止语音
     */
    public void stopRecoard() {
//        mSpeechUtils.stopPeed();
    }

    /**
     * 是否是自己发送的消息
     *
     * @author cyf
     */
    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
     */
    public int getItemViewType(int position) {
        MessageBean messageBean = lists.get(position);

        if (messageBean.isMeSend()) {// 收到的消息
            return IMsgViewType.IMVT_COM_MSG;
        } else {// 自己发送的消息
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        HolderView holderView = null;
        MessageBean messageBean = lists.get(position);
        boolean isMeSend = messageBean.isMeSend();
        if (holderView == null) {
            holderView = new HolderView();
            if (isMeSend) {
                convertView = View.inflate(context, R.layout.right_layout,
                        null);
            } else {
                convertView = View.inflate(context, R.layout.left_layout,
                        null);
            }
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        holderView.tv_chat_message = (TextView) convertView.findViewById(R.id.tv_text);
        holderView.ll_group = (LinearLayout) convertView.findViewById(R.id.ll_group);
        // 选择数据来源
        switch (messageBean.getMessageType()) {
            case ChatActivity.TEXT:
                holderView.tv_chat_message.setText(messageBean.getCotent_text());
                break;
            case ChatActivity.BTN:
                holderView.tv_chat_message.setText(messageBean.getCotent_text());
                View btnGroup = convertView.inflate(context, R.layout.layout_item_btn_single, null);
                holderView.ll_group.addView(btnGroup);
                RecyclerView rv_button = btnGroup.findViewById(R.id.rv_button);
                BtnGroupAdapter btnGroupAdapter = new BtnGroupAdapter(context);
                rv_button.setLayoutManager(new GridLayoutManager(context, 1));
                rv_button.setAdapter(btnGroupAdapter);
                btnGroupAdapter.setSetSureOrCancle(new BtnGroupAdapter.SetSureOrCancle() {
                    @Override
                    public void setSureOrCancleListener(String btn_text, boolean isShow) {
                        setSureOrCancle.setSureOrCancleListener(btn_text, isShow);
                    }
                });
                btnGroupAdapter.setList(messageBean.getBtns());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                convertView.setLayoutParams(layoutParams);
                break;
            case ChatActivity.CHECK_MORE:
                holderView.tv_chat_message.setText(messageBean.getCotent_text());
                setDiseaseMessage.setDiseaseMessageListener(messageBean.getCheckMoreBeans());
                break;
        }
        String content;
        if (!messageBean.isHistory()) {
            if (isReceive && isOpenSound && IMsgViewType.IMVT_COM_MSG != 1) {
                try {
                    content = messageBean.getCotent_text();
                } catch (Exception e) {
                    content = "";
                }
                String text;
                if ("".equals(content)) {
                    text = holderView.tv_chat_message.getText().toString();
                } else {
                    text = content;
                }
                if (!TextUtils.isEmpty(text) && !messageBean.isOffLine()) {
                    //  读出问题内容
//                    mSpeechUtils.startPeed(text, null);
                }
            }
            isReceive = false;
        }
        return convertView;
    }

    class HolderView {
        TextView tv_chat_message;
        LinearLayout ll_group;
    }

    public boolean isEnable(int position) {
        boolean isEnable = true;
        for (int i = position + 1; i < lists.size(); i++) {
            if (!lists.get(i).isSystemMessage()) {
                isEnable = false;
                break;
            }
        }
        return isEnable;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    private SetSureOrCancle setSureOrCancle;

    // 评跳转回调接口
    public interface SetSureOrCancle {
        void setSureOrCancleListener(String btn_text, boolean isShow);
    }

    public void setSetSureOrCancle(SetSureOrCancle setSureOrCancle) {
        this.setSureOrCancle = setSureOrCancle;
    }

    private SetDiseaseMessage setDiseaseMessage;

    // 选择病症横向列表回调接口
    public interface SetDiseaseMessage {
        void setDiseaseMessageListener(List<CheckMoreBean> checkMoreBeans);
    }

    public void setSetDiseaseMessage(SetDiseaseMessage setDiseaseMessage) {
        this.setDiseaseMessage = setDiseaseMessage;
    }
}
