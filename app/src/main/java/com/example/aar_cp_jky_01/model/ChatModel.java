package com.example.aar_cp_jky_01.model;

import android.support.v4.os.IResultReceiver;

import com.example.aar_cp_jky_01.base.IRequestCallback;
import com.example.aar_cp_jky_01.bean.CreateUserBean;
import com.example.aar_cp_jky_01.bean.IssueBean;
import com.example.aar_cp_jky_01.bean.WelcomeBean;
import com.example.aar_cp_jky_01.contract.IChatContract;
import com.example.aar_cp_jky_01.http.ApiService;
import com.example.aar_cp_jky_01.http.IRetrofitServer;
import com.example.aar_cp_jky_01.http.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ChatModel implements IChatContract.IChatModel {
    /**
     * 创建客户
     *
     * @param params
     * @param iRequestCallback
     */
    @Override
    public void getCreateUser(HashMap<String, Object> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitServer.class)
                .doCreateUser(ApiService.POST_ASK, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreateUserBean>() {
                    @Override
                    public void accept(CreateUserBean createUserBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(createUserBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onFailed(throwable.toString());
                        }
                    }
                });

    }

    /**
     * 查询历史
     *
     * @param params
     * @param iRequestCallback
     */
    @Override
    public void getHistory(HashMap<String, Object> params, IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitServer.class)
                .doCreateUser(ApiService.POST_ASK, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CreateUserBean>() {
                    @Override
                    public void accept(CreateUserBean createUserBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(createUserBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onFailed(throwable.toString());
                        }
                    }
                });
    }

    /**
     * 查询欢迎语
     *
     * @param params
     * @param iRequestCallback
     */
    @Override
    public void getWelcome(HashMap<String, Object> params, IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitServer.class)
                .doWelcome(ApiService.POST_WELCOME, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WelcomeBean>() {
                    @Override
                    public void accept(WelcomeBean welcomeBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(welcomeBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onFailed(throwable.toString());
                        }
                    }
                });
    }

    /**
     * 查询问题结果
     *
     * @param params
     * @param iRequestCallback
     */
    @Override
    public void getIssue(HashMap<String, Object> params, IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitServer.class)
                .doIssue(ApiService.POST_ASK, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<IssueBean>() {
                    @Override
                    public void accept(IssueBean issueBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(issueBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onFailed(throwable.toString());
                        }
                    }
                });
    }
}
