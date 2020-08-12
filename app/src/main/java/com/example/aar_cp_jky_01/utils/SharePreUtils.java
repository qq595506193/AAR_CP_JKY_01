package com.example.aar_cp_jky_01.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * xml操作
 *
 * @author jack_tang
 */
public class SharePreUtils {

    public static final String USER_INFO = "user_info";

    public static SharedPreferences getSharePreFernces(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static void saveThemei(Context ctx, int i) {
        SharedPreferences preferences = getSharePreFernces(ctx, "themeid");
        preferences.edit().putInt("themei", i).commit();
    }

    public static boolean getSoundsBoolean(Context context) {
        SharedPreferences preferences = getSharePreFernces(context, USER_INFO);
        return preferences.getBoolean("SOUNDS_BOOLEAN", false);
    }

    public static void saveSoundsBoolean(Context context, Boolean aBoolean) {
        SharedPreferences preferences = getSharePreFernces(context, USER_INFO);
        preferences.edit().putBoolean("SOUNDS_BOOLEAN", aBoolean).commit();
    }

    public static String getId(Context context) {
        SharedPreferences preferences = getSharePreFernces(context, USER_INFO);
        return preferences.getString("LOGINID", "");
    }

    public static void saveId(Context context, String id) {
        SharedPreferences preferences = getSharePreFernces(context, USER_INFO);
        preferences.edit().putString("LOGINID", id).commit();
    }


}
