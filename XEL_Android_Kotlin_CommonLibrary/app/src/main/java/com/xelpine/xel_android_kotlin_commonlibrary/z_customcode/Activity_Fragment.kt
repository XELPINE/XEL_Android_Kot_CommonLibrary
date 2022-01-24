package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityFragmentBinding
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivitySkeletonBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.FragmentInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Fragment
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Skeleton
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Fragment
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Skeleton

class Activity_Fragment : XELActivity_Base(), FragmentInterface
{
    // DataBinding
    private lateinit var mBinding: ActivityFragmentBinding
    // ViewModel
    private val mViewModel: ViewModel_Fragment by viewModels{ ViewModelFactory_Fragment(application, this, this, this) }

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_fragment)

        mBinding.lifecycleOwner = this
        mBinding.framgentViewModel = mViewModel
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
        /**
         * 기본세팅
         */
        setSupportActionBar(mBinding.toolbar)

        // 타이틀 세팅
        mBinding.toolbar.title = "Fragment Sample"

        // 뒤로가기 버튼
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
    }

    override fun DisplayLandscapeAfter() {
    }

    override fun DisplayPortraitAfter() {
    }

    override fun initAfterLogic() {
    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}