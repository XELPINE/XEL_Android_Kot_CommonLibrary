package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

class XELDisplayUtil {

    companion object {

        private val xDisplayMetrics = Resources.getSystem().displayMetrics
        private val density = xDisplayMetrics.density
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
        fun isTablet(cxt: Context): Boolean {
            val portrait_width_pixel = Math.min(
                cxt.resources.displayMetrics.widthPixels,
                cxt.resources.displayMetrics.heightPixels
            )
            val dots_per_virtual_inch = cxt.resources.displayMetrics.densityDpi
            val virutal_width_inch = (portrait_width_pixel / dots_per_virtual_inch).toFloat()
            return if (virutal_width_inch <= 2) {
                false
            } else {
                true
            }
        }

        /**
         * DIP를 PIXEL로 변경
         * @param dp
         * @return
         */
        fun dpToPixel(dp: Float): Int {
            val metrics = Resources.getSystem().displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px)
        }

    }
}
