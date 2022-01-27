package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.receiver

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xelpine.xel_android_kotlin_commonlibrary.Activity_MainMenu
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.Date.from
import java.util.GregorianCalendar.from

class XELAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val intent = Intent(context, Activity_MainMenu::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder : NotificationCompat.Builder = NotificationCompat.Builder(context, "XEL ALARAM CHANNEL ID")
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("TITLE")
            .setContentText("TEXT")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))
            //.setFullScreenIntent(fullScreenPendingIntent, true)

        val notiManager = NotificationManagerCompat.from(context)
        notiManager.notify(10, builder.build())

    }
}