package com.example.aar_cp_jky_01.interceptor;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.aar_cp_jky_01.application.App;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet preferences = (HashSet) App.getContext().getSharedPreferences("config",
                Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (Object cookie : preferences) {
                builder.addHeader("Cookie", (String) cookie);
                //LogUtils.e("cookie===="+cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
