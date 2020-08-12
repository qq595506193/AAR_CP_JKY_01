package com.example.aar_cp_jky_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aar_cp_jky_01.base.BaseActivity;
import com.example.aar_cp_jky_01.chat_view.ChatActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        startActivity(new Intent(this, ChatActivity.class));
        finish();
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(Intent intent) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}