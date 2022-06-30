package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.HiltInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Hilt

class ViewModelFactory_Hilt (
    var application: Application,
    var viewInterface: HiltInterface
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel_Hilt(application,viewInterface) as T
    }
}