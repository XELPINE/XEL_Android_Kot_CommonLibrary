package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELCommonReceiver

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xelpine.xel_android_kotlin_commonlibrary.Activity_MainMenu
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELNotificationUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.Date.from
import java.util.GregorianCalendar.from

class XELAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

//        val mainMenuIntent = Intent(context, Activity_MainMenu::class.java)
//        mainMenuIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
//            mainMenuIntent, PendingIntent.FLAG_UPDATE_CURRENT)

//        val builder : NotificationCompat.Builder = NotificationCompat.Builder(context, "XEL ALARAM CHANNEL ID")
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setSmallIcon(R.mipmap.ic_launcher)
//            .setContentTitle(intent.getStringExtra("TITLE"))
//            .setContentText(intent.getStringExtra("TEXT"))
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setVibrate(LongArray(0))
//            //.setFullScreenIntent(fullScreenPendingIntent, true)
//
//        val notiManager = NotificationManagerCompat.from(context)
//        notiManager.notify(intent.getIntExtra("ID", -1), builder.build())

        XELNotificationUtil.createCommonNotification(context, "XEL ALARM CHANNEL ID",
            intent.getIntExtra("ID", -1), intent.getStringExtra("TITLE"), intent.getStringExtra("TEXT"))

    }
}