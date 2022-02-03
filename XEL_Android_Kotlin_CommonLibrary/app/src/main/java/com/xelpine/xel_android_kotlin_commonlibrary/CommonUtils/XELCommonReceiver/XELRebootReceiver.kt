package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELCommonReceiver

import android.app.Activity
import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xelpine.xel_android_kotlin_commonlibrary.Activity_MainMenu
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELAlarmUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.Date.from
import java.util.GregorianCalendar.from

class XELRebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        XELAlarmUtil.createAlarm(context, 4, "XELRebootReceiver Title 4", "XELRebootReceiver Text 4", 60000)
        XELAlarmUtil.createAlarm(context, 5, "XELRebootReceiver Title 5", "XELRebootReceiver Text 5", 60000)
        XELAlarmUtil.createAlarm(context, 6, "XELRebootReceiver Title 6", "XELRebootReceiver Text 6", 60000)

//        val alarmManager : AlarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
//
//        var alarmIntent = Intent(context, XELAlarmReceiver::class.java)
//
//        alarmIntent.putExtra("TITLE", "REBOOT TITLE")
//        alarmIntent.putExtra("TEXT", "REBOOT TEXT")
//
//        var pendingIntent = PendingIntent.getBroadcast(context, 111, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//
//        /**
//         * Intent 플래그
//         *    FLAG_ONE_SHOT : 한번만 사용하고 다음에 이 PendingIntent가 불려지면 Fail을 함
//         *    FLAG_NO_CREATE : PendingIntent를 생성하지 않음. PendingIntent가 실행중인것을 체크를 함
//         *    FLAG_CANCEL_CURRENT : 실행중인 PendingIntent가 있다면 기존 인텐트를 취소하고 새로만듬
//         *    FLAG_UPDATE_CURRENT : 실행중인 PendingIntent가 있다면  Extra Data만 교체함
//         */
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 15000, pendingIntent); //10초뒤 알람
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 15000, pendingIntent);
//        } else {
//            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 15000, pendingIntent);
//
//        }
    }
}