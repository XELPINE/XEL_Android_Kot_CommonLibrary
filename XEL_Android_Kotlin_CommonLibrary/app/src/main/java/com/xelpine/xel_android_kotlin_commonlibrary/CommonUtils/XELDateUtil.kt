package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import java.text.SimpleDateFormat
import java.util.*

object XELDateUtil
{
    const val DATEUTIL_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"

    /**
     * 오늘날짜 구하기 (YYYY-MM-DD)
     * @return
     */
    fun GetTodayWithFormat(strDateFormat: String?): String
    {
        val simpleDateFormat = SimpleDateFormat(strDateFormat)
        val todaCal = Calendar.getInstance()

        return simpleDateFormat.format(todaCal.time)
    }
}