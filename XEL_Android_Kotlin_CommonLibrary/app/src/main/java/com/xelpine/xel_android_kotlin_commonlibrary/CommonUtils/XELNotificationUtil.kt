package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.xelpine.xel_android_kotlin_commonlibrary.R

/**
 * 노티피케이션 생성 유틸
 */
object XELNotificationUtil
{
    /**
     * 노티피케이션 채널 생성 샘플.
     */
    open fun createNotificationChannel (context: Context)
    {
        /**
         * 노티피케이션 채널 생성하기 안드로이드 버전 오레오 이상부터 필요
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "XEL ALARM CHANNEL ID" // 채널 아이디
            val channelName: CharSequence = "XEL ALARM CHANNEL NAME" //채널 이름
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager: NotificationManager = context.getSystemService<NotificationManager>(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * 기본적인 Notification 생성 코드
     */
    open fun createCommonNotification (context: Context, channelId : String,
                                       notificationId : Int, title : String?, text : String?)
    {
        val builder : NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))
        //.setFullScreenIntent(fullScreenPendingIntent, true)

        val notiManager = NotificationManagerCompat.from(context)
        notiManager.notify(notificationId, builder.build())
    }
}