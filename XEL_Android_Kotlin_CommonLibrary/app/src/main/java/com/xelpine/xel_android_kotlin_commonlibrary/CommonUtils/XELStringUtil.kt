package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.text.TextUtils
import java.lang.Exception
import java.text.DecimalFormat

object XELStringUtil {
    /**
     * 소수점 이하 버리기 코드. 안버려지면 기본 코드 리턴
     * @param data
     * @return
     */
    fun GetStringWithDecimalPoint(data: String): String {
        return try {
            data.substring(0, data.indexOf("."))
        } catch (e: Exception) {
            data
        }
    }

    /**
     * 날짜를 입력받아 yyyy.MM.dd 형태로 변경하여 돌려준다.
     * 자르지 못할 경우 원본 반환.
     * @return
     */
    fun SetFormatYYYYMMDD(date: String): String {
        return try {
            val year = date.substring(0, 4)
            val month = date.substring(4, 6)
            val day = date.substring(6, 8)
            "$year.$month.$day"
        } catch (e: Exception) {
            date
        }
    }

    /**
     * 숫자에 천단위마다 콤마 넣기
     * @return String
     */
    fun SetFormatDecimal1000(num: String): String {
        var num = num
        return try {
            num = GetStringWithDecimalPoint(num)
            val intNum = num.toInt()
            val df = DecimalFormat("#,###")
            df.format(intNum.toLong()).toString()
        } catch (e: Exception) {
            num
        }
    }

    /**
     * 숫자에 천단위마다 콤마 넣기
     * @return String
     */
    fun SetFormatDecimal1000withKRW(num: String): String {
        var num = num
        return try {
            num = SetFormatDecimal1000(num)
            if (TextUtils.isEmpty(num)) {
                num
            } else {
                "KRW $num"
            }
        } catch (e: Exception) {
            "KRW $num"
        }
    }
}
