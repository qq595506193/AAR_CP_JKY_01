package com.example.aar_cp_jky_01.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aar_cp_jky_01.R;
import com.example.aar_cp_jky_01.bean.IssueContentBean;
import com.example.aar_cp_jky_01.bean.MessageBtnBean;
import com.example.aar_cp_jky_01.chat_view.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class BtnGroupAdapter extends RecyclerView.Adapter<BtnGroupAdapter.ViewHolder> {
    private Context context;

    private List<IssueContentBean.BtnsBean> btns;

    private ChatActivity chatActivity;


    public BtnGroupAdapter(Context context, ChatActivity chatActivity) {
        this.context = context;
        this.chatActivity = chatActivity;
        btns = new ArrayList<>();
    }

    public BtnGroupAdapter(Context context) {
        this.context = context;

        chatActivity = (ChatActivity) context;
    }

    public void setList(List<IssueContentBean.BtnsBean> btns) {
        if (btns != null) {
            this.btns = btns;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BtnGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_button, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BtnGroupAdapter.ViewHolder holder, int position) {
        IssueContentBean.BtnsBean btnsBean = btns.get(position);
        if ((btnsBean.getOption_Confirm() == null ? "" : btnsBean.getOption_Confirm()).equals("1")) {
            btnsBean.setAsk(true);
        } else {
            btnsBean.setAsk(false);
        }
        holder.btn.setText(Html.fromHtml(btnsBean.getCont()));
//        if (holder.btn.getText().toString().trim().length() > 6) {
//            holder.btn.setTextSize(12);
//        }
        holder.btn.setTag(btnsBean);
        if (!TextUtils.isEmpty(btnsBean.getOption_Color())) {
            holder.btn.setTextColor(Color.parseColor("#" + btnsBean.getOption_Color()));
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.phoneType = "";
                if (btnsBean.isAsk()) {
                    setSureOrCancle.setSureOrCancleListener(btnsBean.getCont(), true);
                } else {
                    setSureOrCancle.setSureOrCancleListener(btnsBean.getCont(), false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return btns.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView btn;

        public ViewHolder(View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }

    private SetSureOrCancle setSureOrCancle;

    //
    public interface SetSureOrCancle {
        void setSureOrCancleListener(String btn_text, boolean isShow);
    }

    public void setSetSureOrCancle(SetSureOrCancle setSureOrCancle) {
        this.setSureOrCancle = setSureOrCancle;
    }
}
