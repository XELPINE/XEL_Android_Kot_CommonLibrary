package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import androidx.activity.viewModels
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivitySkeletonBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.SkeletonInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Skeleton
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Skeleton

class Activity_Skeleton : XELActivity_Base(), SkeletonInterface
{
    // DataBinding
    private lateinit var mBinding: ActivitySkeletonBinding
    // ViewModel
    private val mViewModel: ViewModel_Skeleton by viewModels{ ViewModelFactory_Skeleton(application, this, this, this) }

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
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
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun DisplayLandscapeAfter() {
        TODO("Not yet implemented")
    }

    override fun DisplayPortraitAfter() {
        TODO("Not yet implemented")
    }

    override fun initAfterLogic() {
        TODO("Not yet implemented")
    }

    override fun doPause() {
        TODO("Not yet implemented")
    }

    override fun doResume() {
        TODO("Not yet implemented")
    }

    override fun doDestroy() {
        TODO("Not yet implemented")
    }
}