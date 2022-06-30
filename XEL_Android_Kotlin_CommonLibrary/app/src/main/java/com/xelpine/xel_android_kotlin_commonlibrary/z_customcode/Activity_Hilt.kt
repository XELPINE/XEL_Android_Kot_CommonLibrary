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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Activity_Hilt : XELActivity_Base(), HiltInterface
{
//    // DataBinding
//    private lateinit var mBinding: ActivityHiltBinding
//    // ViewModel
//    private val mViewModel: ViewModel_Hilt by viewModels{ ViewModelFactory_Hilt(application, this) }

    // Adapter
//    lateinit var HiltListAdapter : HiltListAdapter

    @Inject lateinit var classA : HiltClassADto

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