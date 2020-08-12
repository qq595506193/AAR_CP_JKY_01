package com.example.aar_cp_jky_01.http;

import android.text.TextUtils;

import com.example.aar_cp_jky_01.chat_view.ChatActivity;
import com.example.aar_cp_jky_01.utils.SharePreUtils;

public class LoginHttp {

    private static LoginHttp loginHttp;
    private String loginId = "";

    public static LoginHttp getLoginHttp() {
        return new LoginHttp();
    }

    public String getLoginId() {
        if (TextUtils.isEmpty(loginId)) {
            loginId = SharePreUtils.getId(ChatActivity.chatActivity);
        }
        return loginId;
    }

    public void setLoginId(String loginId) {
        SharePreUtils.saveId(ChatActivity.chatActivity, loginId);
        this.loginId = loginId;
    }
}
