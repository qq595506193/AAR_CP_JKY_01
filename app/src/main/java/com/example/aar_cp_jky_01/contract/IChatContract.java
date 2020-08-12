package com.example.aar_cp_jky_01.contract;

import com.example.aar_cp_jky_01.base.BasePresenter;
import com.example.aar_cp_jky_01.base.IBaseModel;
import com.example.aar_cp_jky_01.base.IRequestCallback;
import com.example.aar_cp_jky_01.model.ChatModel;

import java.util.HashMap;

public interface IChatContract {
    abstract class ChatPresenter extends BasePresenter<IChatModel, IChatView> {
        public abstract void getCreateUser(HashMap<String, Object> params);

        public abstract void getHistory(HashMap<String, Object> params);

        public abstract void getWelcome(HashMap<String, Object> params);

        public abstract void getIssue(HashMap<String, Object> params);

        @Override
        public IChatModel getModel() {
            return new ChatModel();
        }
    }

    interface IChatModel extends IBaseModel {
        void getCreateUser(HashMap<String, Object> params, IRequestCallback iRequestCallback);

        void getHistory(HashMap<String, Object> params, IRequestCallback iRequestCallback);

        void getWelcome(HashMap<String, Object> params, IRequestCallback iRequestCallback);

        void getIssue(HashMap<String, Object> params, IRequestCallback iRequestCallback);
    }

    interface IChatView {
        void onCreateUserSuccess(Object result);

        void onCreateUserFailed(Object error);

        void onHistorySuccess(Object result);

        void onHistoryFailed(Object error);

        void onWelcomeSuccess(Object result);

        void onWelcomeFailed(Object error);

        void onIssueSuccess(Object result);

        void onIssueFailed(Object error);
    }
}
