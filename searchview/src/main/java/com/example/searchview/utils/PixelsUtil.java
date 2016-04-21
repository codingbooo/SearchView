package com.example.searchview.utils;

import android.content.Context;

/**
 * Created by Bob on 2016/4/21.
 */
public class PixelsUtil {

    /**
     * 40.0dip ~~~ 40.0
     *
     * @param dip dip
     * @return float
     */
    public static float dip2Float(String dip){
        return Float.valueOf(dip.substring(0, dip.length() - 3));
    }


    /**
     * dip ~~ px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    /**
     * 40.0dip ~~~ px
     * @param context
     * @param dip
     * @return
     */
    public static int dip2px(Context context, String dip) {
        return dip2px(context, dip2Float(dip));
    }

    /**
     * px ~~ dip
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }


}
