package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELExtension.notifyObserver
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivitySkeletonBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.adapter.SkeletonListAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.callback.CommonDataLoadCallback
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.SkeletonInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Skeleton
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Skeleton

class Activity_Skeleton : XELActivity_Base(), SkeletonInterface
{
    // DataBinding
    private lateinit var mBinding: ActivitySkeletonBinding
    // ViewModel
    private val mViewModel: ViewModel_Skeleton by viewModels{ ViewModelFactory_Skeleton(application, this, this, this) }

    // Adapter
    lateinit var skeletonListAdapter : SkeletonListAdapter




    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_skeleton)

        mBinding.lifecycleOwner = this
        mBinding.skeletonViewModel = mViewModel
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
        mBinding.toolbar.title = "자산 현황 조회 결과"

        // 뒤로가기 버튼
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
        skeletonListAdapter = SkeletonListAdapter(mViewModel.skeletonList)
//        skeletonListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
        mBinding.rvList.adapter = skeletonListAdapter
        mBinding.rvList.layoutManager = LinearLayoutManager(this)

        // 리스트 변화 감시
        mViewModel.skeletonList.observe(this, Observer {

            XELLogUtil.e_function(XELGlobalDefine.TAG, "리스트 변화 감시 : " + mViewModel.skeletonList.value?.size)



            mBinding.rvList.adapter?.notifyDataSetChanged()
        })
    }

    override fun DisplayLandscapeAfter() {
    }

    override fun DisplayPortraitAfter() {
    }

    override fun initAfterLogic() {

        XELHandlerUtil.PostDelayed(2000, object : XELHandlerUtil.DelayedCompleteCallback{
            override fun DelayComplete() {


                mViewModel.CommonDataInfoInit(object : CommonDataLoadCallback{
                    override fun CommonDataLoadSuccess() {



                        mViewModel.skeletonList.notifyObserver()
                    }

                    override fun CommonDataLoadFailed() {
                        TODO("Not yet implemented")
                    }
                })


            }
        })



    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}