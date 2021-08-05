package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.view.ViewGroup;


public class XELLayoutUtil
{
    /**
     * 레이아웃 가로 세로 길이 세팅. DIP로 조절된다
     * @param layoutParams
     * @param width
     * @param height
     * @return
     */
    public static ViewGroup.LayoutParams SetLayoutWidthAndHeightWithDIP (ViewGroup.LayoutParams layoutParams, int width, int height)
    {
        layoutParams.width = XELDisplayUtil.Companion.dpToPixel(Float.valueOf(width));
        layoutParams.height = XELDisplayUtil.Companion.dpToPixel(Float.valueOf(height));

        return layoutParams;
    }
}
