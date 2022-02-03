package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityProgressbarBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.ProgressBarInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_ProgressBar
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_ProgressBar
import java.util.*

class Activity_ProgressBar : XELActivity_Base(), ProgressBarInterface
{
    // DataBinding
    private lateinit var mBinding: ActivityProgressbarBinding
    // ViewModel
    private val mViewModel: ViewModel_ProgressBar by viewModels{ ViewModelFactory_ProgressBar(application, this, this, this) }

    // Adapter
//    lateinit var ProgressBarListAdapter : ProgressBarListAdapter


    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_progressbar)

        mBinding.lifecycleOwner = this
        mBinding.progressbarLibraryViewModel = mViewModel
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
        mBinding.toolbar.title = "ProgressBar Sample"

        // 뒤로가기 버튼
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
//        ProgressBarListAdapter = ProgressBarListAdapter(this, mViewModel.ProgressBarList)
////        ProgressBarListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
//        mBinding.rvList.adapter = ProgressBarListAdapter
//        mBinding.rvList.layoutManager = LinearLayoutManager(this)
//
//        // 리스트 변화 감시
//        mViewModel.ProgressBarList.observe(this, Observer {
//
//            XELLogUtil.e_function(XELGlobalDefine.TAG, "리스트 변화 감시 : " + mViewModel.ProgressBarList.value?.size)
//
//
//
//            mBinding.rvList.adapter?.notifyDataSetChanged()
//        })
    }

    override fun displayLandscapeAfter() {


    }

    override fun displayPortraitAfter() {

    }

    override fun initAfterLogic() {

        mBinding.firstBar.setBackgroundDrawableColor(Color.parseColor("#EA80FC"))
        mBinding.firstBar.setProgressDrawableColor(Color.parseColor("#AA00FF"))
        mBinding.firstBar.setProgressPercentage(0.0, true)
        mBinding.firstBar.setAnimationLength(200)
        mBinding.firstBar.showProgressText(false)

        val timer : Timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {

                runOnUiThread {
                    XELHandlerUtil.PostDelayed(0, object : XELHandlerUtil.DelayedCompleteCallback {
                        override fun DelayComplete() {

                            if (mBinding.firstBar.getProgressPercentage() < 100.0)
                            {
                                mBinding.firstBar.setProgressPercentage(
                                    mBinding.firstBar.getProgressPercentage() + 1,
                                    true
                                )
                            }
                        }
                    })
                }


            }
        }, 1000, 200)
    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}