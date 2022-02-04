package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.content.DialogInterface
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.View
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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil





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
        val slide = Slide()
        slide.duration = 300
        slide.slideEdge = Gravity.RIGHT
//        slide.interpolator = LinearOutSlowInInterpolator()

        val explode = Explode()
        explode.duration = 300
        window.enterTransition = slide
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


//        mBinding.btn1.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//
//                XELLogUtil.d_function(XELGlobalDefine.TAG, "BTN 1 클릭")
//
//                val frag1 : Fragment_Test1 = Fragment_Test1()
//
//                //1.매니저 생성
//                val fragmentManager: FragmentManager = supportFragmentManager
//                //2.시작
//                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//                //3.추가, 삭제, 교체
//                fragmentTransaction.replace(mBinding.llFragmentMain.id, frag1)
//                //4.수행
//                fragmentTransaction.commit()
//
//
////                val fragmentManager =supportFragmentManager //매니저생성
//
////                val fragmentTransaction = fragmentManager.beginTransaction() //시작
//
////                fragmentTransaction.replace(, chgFragment) //교체
////
////                fragmentTransaction.commit() //실행
//
//            }
//        })

        val frag1 : Fragment_Test1 = Fragment_Test1()

        //1.매니저 생성
        val fragmentManager: FragmentManager = supportFragmentManager
        //2.시작
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        //3.추가, 삭제, 교체
        fragmentTransaction.replace(mBinding.llFragmentMain.id, frag1)
        //4.수행
        fragmentTransaction.commit()
    }

    override fun initData() {
    }

    override fun displayLandscapeAfter() {
    }

    override fun displayPortraitAfter() {
    }

    override fun initAfterLogic() {
    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doStart() {
    }

    override fun doStop() {
    }

    override fun doDestroy() {
    }
}