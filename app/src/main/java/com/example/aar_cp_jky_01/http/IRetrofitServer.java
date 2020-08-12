package com.example.aar_cp_jky_01.http;

import com.example.aar_cp_jky_01.bean.CreateUserBean;
import com.example.aar_cp_jky_01.bean.HistoryBean;
import com.example.aar_cp_jky_01.bean.IssueBean;
import com.example.aar_cp_jky_01.bean.WelcomeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IRetrofitServer {


    /**
     * 创建客户
     *
     * @param apiUrl
     * @param params
     * @return
     */
    @GET
    Observable<CreateUserBean> doCreateUser(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    /**
     * 查询欢迎语
     *
     * @param apiUrl
     * @param params
     * @return
     */
    @GET
    Observable<WelcomeBean> doWelcome(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    /**
     * 查询历史
     *
     * @param apiUrl
     * @param params
     * @return
     */
    @GET
    Observable<HistoryBean> doHistory(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    /**
     * 查询问题结果
     *
     * @param apiUrl
     * @param params
     * @return
     */
    @GET
    Observable<IssueBean> doIssue(@Url String apiUrl, @QueryMap HashMap<String, Object> params);
}
