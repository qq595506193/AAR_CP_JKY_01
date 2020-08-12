package com.example.aar_cp_jky_01.application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.example.aar_cp_jky_01.chat_view.ChatActivity;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // 这里可以替换为detectAll() 就包括了磁盘读写和网络I/O
                .penaltyLog() //打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() //探测SQLite数据库操作
                .penaltyLog() //打印logcat
                .penaltyDeath()
                .build());
        super.onCreate();
        context = getApplicationContext();

    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }


}