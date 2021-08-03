package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;


public class XELDisplayUtil {
    private final static DisplayMetrics xDisplayMetrics = Resources.getSystem().getDisplayMetrics();
    private final static float density = xDisplayMetrics.density;

//    public static int dipToPixel(float a_nNum)
//    {
//        int nChangeNum = (int) (a_nNum * density + 0.5f);
//        return nChangeNum;
//    }

    /**
     * 태블릿인가 유무
     * @param cxt
     * @return
     */
    public static boolean isTablet(Context cxt) {
        int portrait_width_pixel = Math.min(cxt.getResources().getDisplayMetrics().widthPixels, cxt.getResources().getDisplayMetrics().heightPixels);
        int dots_per_virtual_inch = cxt.getResources().getDisplayMetrics().densityDpi;
        float virutal_width_inch = portrait_width_pixel / dots_per_virtual_inch;
        if (virutal_width_inch <= 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * DIP를 PIXEL로 변경
     * @param dp
     * @return
     */
    public static int dpToPixel(float dp)
    {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
