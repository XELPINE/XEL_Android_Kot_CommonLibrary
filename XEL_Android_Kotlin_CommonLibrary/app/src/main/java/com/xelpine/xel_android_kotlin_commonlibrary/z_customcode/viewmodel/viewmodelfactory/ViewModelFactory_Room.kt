package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.RoomInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Room

class ViewModelFactory_Room (
    var application: Application,
    var context : Context,
    var activity: AppCompatActivity,
    var viewInterface: RoomInterface
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel_Room(application, context, activity, viewInterface) as T
    }
}