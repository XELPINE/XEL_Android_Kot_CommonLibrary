package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class XELDateUtil {

    public static final String DATEUTIL_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 오늘날짜 구하기 (YYYY-MM-DD)
     * @return
     */
    public static String GetTodayWithFormat (String strDateFormat)
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        Calendar todaCal = Calendar.getInstance();
        simpleDateFormat.format(todaCal.getTime());

        String result = simpleDateFormat.format(todaCal.getTime());

        return result;
    }
}
