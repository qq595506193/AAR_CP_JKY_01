package com.example.aar_cp_jky_01.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 系统工具用于检查系统状态,如网络,内存卡加载状态
 *
 * @author zhao
 */
public class SystemUtils {



    private static String PREF_KEY_UUID = "uuid";

    /**
     * 获取uuid
     */
    public static String getUUID() {
        String uuid = getDeviceUUid();
        if (TextUtils.isEmpty(uuid)) {
            uuid = getAppUUid();
        }
        return uuid;
    }


    public static String getAndroidID() {
        String id = Settings.Secure.getString(
                Utils.getApp().getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
        return id == null ? "" : id;
    }

    private static String getDeviceUUid() {
        String androidId = DeviceUtils.getAndroidID();
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) androidId.hashCode() << 32));
        return deviceUuid.toString();
    }

    private static String getAppUUid() {
        String uuid = SPUtils.getInstance().getString(PREF_KEY_UUID);
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            //这里需要保存到SharedPreference中
            SPUtils.getInstance().put(PREF_KEY_UUID, uuid);
        }
        return uuid;
    }




    /**
     * 隐藏输入键盘
     */
    public static void hideSoftBord(Context context, EditText mEditText) {
        if (mEditText != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Service.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
    }

    /**
     * 打开输入键盘
     */
    public static void openSoftBord(Context context, EditText mEditText) {
        if (mEditText != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context
                    .getSystemService(Service.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(mEditText, 0);
        }
    }




    /**
     * 获取手机唯一ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(final Context context) {
        SharedPreferences preferences = context.getSharedPreferences("IMEI", Context.MODE_PRIVATE);
        String uid = preferences.getString("uid", null);
        if (uid != null) return uid;
        SharedPreferences.Editor editor = preferences.edit();
        uid = UUID.randomUUID().toString();
        editor.putString("uid", uid);
        editor.commit();
        return uid;
    }


    public static CharSequence parseToText(String content) {

        SpannableStringBuilder builder = new SpannableStringBuilder();
        Pattern mPattern = Pattern.compile("<a>([^<]*)</a>");
        final Matcher matcher = mPattern.matcher(content);
        int endnume = 0;
        while (matcher.find()) {
            final CharSequence con = matcher.group(1);
            builder.append(content.subSequence(endnume, matcher.start()));
            builder.append(con);
            endnume = matcher.end();
            builder.setSpan(new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
//                                    chatWithServiceFromDrugs(con.toString());
                                }

                                @Override
                                public void updateDrawState(TextPaint ds) {
                                    super.updateDrawState(ds);
                                    ds.setColor(Color.RED);       //设置文件颜色
                                    ds.setUnderlineText(true);      //设置下划线
                                }
                            }, builder.length() - matcher.group(1).length(), builder.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            builder.setSpan(new UnderlineSpan(), builder.length() - matcher.group(1).length(), builder.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (content.length() > endnume) {
            builder.append(content.subSequence(endnume, content.length()));
        }
        return builder;
    }

    /**
     * 字符串转换
     *
     * @return
     */

    public static String getHtmlChange(String str) {
        return str.replace("</br>", "|br|").replace("\n", "|br|").replace("null", "").replace("<", "&#60;").replace(">", "&#62;").replace("|br|", "<br>");
    }

    /**
     * 字符串转换
     *
     * @return
     */

    public static String getStringChange(String str) {
        return str.replace("</br>", "").replace("\n", "").replace("null", "").replace("&#60;", "").replace("&#62;", "").replace("|br|", "").replace("<br>", "");
    }


    private static final String HEX_STRING = "0123456789ABCDEF";

    /**
     * 把中文字符转换为带百分号的浏览器编码
     *
     * @param word
     * @return
     */
    public static String toBrowserCode(String word) {
        byte[] bytes = word.getBytes();

        //不包含中文，不做处理
        if (bytes.length == word.length())
            return word;

        StringBuilder browserUrl = new StringBuilder();
        String tempStr = "";

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            //不需要处理
            if ((int) currentChar <= 256) {

                if (tempStr.length() > 0) {
                    byte[] cBytes = tempStr.getBytes();

                    for (int j = 0; j < cBytes.length; j++) {
                        browserUrl.append('%');
                        browserUrl.append(HEX_STRING.charAt((cBytes[j] & 0xf0) >> 4));
                        browserUrl.append(HEX_STRING.charAt((cBytes[j] & 0x0f) >> 0));
                    }
                    tempStr = "";
                }

                browserUrl.append(currentChar);
            } else {
                //把要处理的字符，添加到队列中
                tempStr += currentChar;
            }
        }
        return browserUrl.toString();
    }

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                TypedValue typedValue = new TypedValue();
                activity.getTheme().resolveAttribute(colorResId, typedValue, true);
                window.setStatusBarColor(typedValue.data);
                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
