package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityHiltBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.HiltClassADto
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.HiltClassBDto
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.HiltInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Hilt
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Hilt
import kotlinx.coroutines.*

class Activity_Hilt : XELActivity_Base(), HiltInterface
{
//    // DataBinding
//    private lateinit var mBinding: ActivityHiltBinding
//    // ViewModel
//    private val mViewModel: ViewModel_Hilt by viewModels{ ViewModelFactory_Hilt(application, this) }

    // Adapter
//    lateinit var HiltListAdapter : HiltListAdapter

    lateinit var classA : HiltClassADto

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

//        // 데이터 바인딩
//        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_hilt)
//
//        mBinding.lifecycleOwner = this
//        mBinding.hiltViewModel = mViewModel

        setContentView(R.layout.activity_hilt)
    }

    override fun setTheme() {
    }

    override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.SLIDE_RIGHT
    }

    override fun setWindowTransitions() {
    }

    override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
    }

    override fun initData() {
    }

    override fun displayLandscapeAfter() {
    }

    override fun displayPortraitAfter() {
    }

    override fun initAfterLogic() {

//        mViewModel.viewModelInit()

        XELLogUtil.e(XELGlobalDefine.TAG, "CLASS B :" + HiltClassADto(HiltClassBDto("asdflkjasdfk")).classB.classBtitle)


        XELLogUtil.d_function(XELGlobalDefine.TAG, "CoroutineScope 시작")
        CoroutineScope(Dispatchers.Default).launch()
        {
            val job1 = launch {
                XELLogUtil.d_function(XELGlobalDefine.TAG, "JOB1")
                delay(3000)
                XELLogUtil.d_function(XELGlobalDefine.TAG, "JOB1-1")

            }

            testFun()

            val job2 = launch {
                XELLogUtil.d_function(XELGlobalDefine.TAG, "JOB2")
            }

            val job3 = launch {
                XELLogUtil.d_function(XELGlobalDefine.TAG, "JOB3")
            }
        }
        XELLogUtil.d_function(XELGlobalDefine.TAG, "CoroutineScope 끝")
    }

    fun testFun ()
    {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "testFun")
        //delay(3000)
        XELLogUtil.d_function(XELGlobalDefine.TAG, "testFun end")
    }

    override fun doStart() {
    }

    override fun doResume() {
    }

    override fun doPause() {
    }

    override fun doStop() {
    }

    override fun doDestroy() {
    }
}