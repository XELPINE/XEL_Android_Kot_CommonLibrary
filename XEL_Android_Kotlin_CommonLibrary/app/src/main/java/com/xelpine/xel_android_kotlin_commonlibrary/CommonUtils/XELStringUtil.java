package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class XELStringUtil {

    /**
     * 소수점 이하 버리기 코드. 안버려지면 기본 코드 리턴
     * @param data
     * @return
     */
    public static String GetStringWithDecimalPoint (String data)
    {
        try
        {
            return data.substring(0, data.indexOf("."));
        }
        catch (Exception e)
        {
            return data;
        }
    }

    /**
     * 날짜를 입력받아 yyyy.MM.dd 형태로 변경하여 돌려준다.
     * 자르지 못할 경우 원본 반환.
     * @return
     */
    public static String SetFormatYYYYMMDD (String date)
    {

        try
        {
            String year = date.substring(0, 4);
            String month = date.substring(4, 6);
            String day = date.substring(6, 8);

            return year + "." + month + "." + day;
        }
        catch (Exception e)
        {
            return date;
        }
    }

    /**
     * 숫자에 천단위마다 콤마 넣기
     * @return String
     * */
    public static String SetFormatDecimal1000 (String num)
    {
        try
        {
            num = GetStringWithDecimalPoint(num);
            int intNum = Integer.parseInt(num);
            DecimalFormat df = new DecimalFormat("#,###");

            return String.valueOf(df.format(intNum));
        }
        catch (Exception e)
        {
            return num;
        }
    }

    /**
     * 숫자에 천단위마다 콤마 넣기
     * @return String
     * */
    public static String SetFormatDecimal1000withKRW (String num)
    {
        try
        {
            num = SetFormatDecimal1000 (num);

            if (TextUtils.isEmpty(num))
            {
                return num;
            }
            else
            {
                return "KRW " + num;
            }
        }
        catch (Exception e)
        {
            return "KRW " + num;
        }
    }
}
