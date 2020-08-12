package com.example.aar_cp_jky_01.base;

import java.text.ParseException;

public interface IRequestCallback {
    void onSuccess(Object result) throws ParseException;

    void onFailed(Object error);
}
