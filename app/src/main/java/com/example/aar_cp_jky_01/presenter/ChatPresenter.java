package com.example.aar_cp_jky_01.presenter;

import android.print.PrinterId;

import com.example.aar_cp_jky_01.base.IRequestCallback;
import com.example.aar_cp_jky_01.contract.IChatContract;
import com.example.aar_cp_jky_01.model.ChatModel;

import java.text.ParseException;
import java.util.HashMap;

public class ChatPresenter extends IChatContract.ChatPresenter {


    /**
     * 创建客户
     *
     * @param params
     */
    @Override
    public void getCreateUser(HashMap<String, Object> params) {
        model.getCreateUser(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (view != null) {

                    view.onCreateUserSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onCreateUserFailed(error);
                }
            }
        });
    }

    /**
     * 请求历史
     *
     * @param params
     */
    @Override
    public void getHistory(HashMap<String, Object> params) {
        model.getHistory(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (view != null) {
                    view.onHistorySuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onHistoryFailed(error);
                }
            }
        });
    }

    /**
     * 查询欢迎语
     *
     * @param params
     */
    @Override
    public void getWelcome(HashMap<String, Object> params) {
        model.getWelcome(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (view != null) {
                    view.onWelcomeSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onWelcomeFailed(error);
                }
            }
        });
    }

    /**
     * 查询问题结果
     *
     * @param params
     */
    @Override
    public void getIssue(HashMap<String, Object> params) {
        model.getIssue(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (view != null) {
                    view.onIssueSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onIssueFailed(error);
                }
            }
        });
    }
}
