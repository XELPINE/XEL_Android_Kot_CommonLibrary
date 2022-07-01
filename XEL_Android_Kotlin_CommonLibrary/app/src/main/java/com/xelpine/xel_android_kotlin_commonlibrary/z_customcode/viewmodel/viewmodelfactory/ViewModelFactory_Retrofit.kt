package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.RetrofitInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Retrofit

class ViewModelFactory_Retrofit (
    var application: Application,
    var viewInterface: RetrofitInterface
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel_Retrofit(application,viewInterface) as T
    }
}