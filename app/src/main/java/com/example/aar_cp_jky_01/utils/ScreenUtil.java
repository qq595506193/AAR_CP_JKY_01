package com.example.aar_cp_jky_01.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by TriumphalSun
 * on 2019/9/27 0027
 * com.dmsj.newask.utils name of the package in which the new file is created
 */
public class ScreenUtil {

    static double scale;
    static int screenWidth = 0, screenHeight = 0;

    public static void init(Context context) {
        scale = context.getResources().getDisplayMetrics().density;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

//    public static int getScreenHeight() {
//        return screenHeight;
//    }
//
//    public static int getScreenWidth() {
//        return screenWidth;
//    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getTotalHeightofListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //mView.measure(0, 0);
            totalHeight += mView.getMeasuredHeight();
            Log.d("数据" + i, String.valueOf(totalHeight));
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        Log.d("数据", "listview总高度=" + params.height);
        listView.setLayoutParams(params);
        listView.requestLayout();
        return totalHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }


    private static DisplayMetrics sDM = Resources.getSystem().getDisplayMetrics();

//    public static int getScreenWidth() {
//        return sDM.widthPixels;
//    }

//    public static int getScreenHeight() {
//        return sDM.heightPixels;
//    }

    public static float getDensity() {
        return sDM.density;
    }

    public static int dpToPx(int dp) {
        return dpToPx((float) dp);
    }

    public static int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sDM);
    }

    public static int spToPx(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, sDM);
    }

    public static int pxToDp(int px) {
        return Math.round(px / getDensity());
    }


}
