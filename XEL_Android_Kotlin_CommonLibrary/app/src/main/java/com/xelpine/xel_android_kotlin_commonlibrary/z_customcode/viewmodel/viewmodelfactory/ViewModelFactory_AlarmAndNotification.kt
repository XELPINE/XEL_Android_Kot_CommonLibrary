package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.AlarmAndNotificationInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_AlarmAndNotification

class ViewModelFactory_AlarmAndNotification (
    var application: Application,
    var context : Context,
    var activity: AppCompatActivity,
    var viewInterface: AlarmAndNotificationInterface
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel_AlarmAndNotification(application, context, activity, viewInterface) as T
    }
}