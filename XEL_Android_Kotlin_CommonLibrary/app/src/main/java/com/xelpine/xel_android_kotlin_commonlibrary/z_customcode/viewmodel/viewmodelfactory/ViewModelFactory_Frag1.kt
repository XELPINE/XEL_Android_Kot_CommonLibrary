package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.Frag1Interface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Frag1

class ViewModelFactory_Frag1 (
    var application: Application,
    var context : Context,
    var activity: AppCompatActivity,
    var viewInterface: Frag1Interface
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel_Frag1(application, context, activity, viewInterface) as T
    }
}