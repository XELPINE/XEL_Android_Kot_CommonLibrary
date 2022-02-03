package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELCommonReceiver.XELAlarmReceiver

/**
 * 알람매니저 관련 유틸.
 * 알람을 쉽게 생성하도록 해준다.
 */
object XELAlarmUtil
{

    /**
     * 알람 생성. 타이틀, text 등은 경우에 따라 바뀌도록 해야 한다. 추후 제네릭 형태를 취하도록 개발하는게 좋을 듯.
     * alarmID를 다르게 하면 여러개가 울린다.
     */
    open fun createAlarm (context: Context, alarmId : Int, title : String, text : String, fewSecondsLater : Int)
    {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "createAlarm (알람생성) ID : $alarmId / Title : $title / Text : $text")

        val alarmManager : AlarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager

        var alarmIntent = Intent(context, XELAlarmReceiver::class.java)

        alarmIntent.putExtra("ID", alarmId)
        alarmIntent.putExtra("TITLE", title)
        alarmIntent.putExtra("TEXT", text)

        var pendingIntent = PendingIntent.getBroadcast(context, alarmId, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

        /**
         * Intent 플래그
         *    FLAG_ONE_SHOT : 한번만 사용하고 다음에 이 PendingIntent가 불려지면 Fail을 함
         *    FLAG_NO_CREATE : PendingIntent를 생성하지 않음. PendingIntent가 실행중인것을 체크를 함
         *    FLAG_CANCEL_CURRENT : 실행중인 PendingIntent가 있다면 기존 인텐트를 취소하고 새로만듬
         *    FLAG_UPDATE_CURRENT : 실행중인 PendingIntent가 있다면  Extra Data만 교체함
         */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + fewSecondsLater, pendingIntent); //10초뒤 알람
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + fewSecondsLater, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + fewSecondsLater, pendingIntent);
        }
    }

}