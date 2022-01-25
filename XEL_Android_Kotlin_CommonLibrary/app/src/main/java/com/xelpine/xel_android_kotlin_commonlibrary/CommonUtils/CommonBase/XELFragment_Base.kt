package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil

/**
 * 베이스 프래그먼트.
 */
abstract class XELFragment_Base : Fragment()
{
    /**
     * onCreate() 최상단에서 처리가 필요한 경우 사용. (ex : 윈도우 속성 변경 등..)
     */
    protected open fun beforeCreateView()
    {

    }

    /**
     * 초기화 루틴을 실제로 구현하는 부분.
     */
    protected open fun doCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "doCreate")

        return null
    }

    /**
     * 레이아웃 세팅. findViewById, setListener 등
     */
    protected abstract fun initLayout()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val r = Resources.getSystem()
        val config = r.configuration
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttach ORIENTATION_LANDSCAPE")

            displayLandscapeAfter()

        } else {
            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttach ORIENTATION_PORTRAIT")
            displayPortraitAfter()

        }
    }

    /**
     * 기본값 세팅
     */
    protected abstract fun initData()

    /**
     * 화면이 가로로 변했을 때 (진입 시, 진입 후 화면 전환 시 모두 다)
     */
    protected abstract fun displayLandscapeAfter()

    /**
     * 화면이 세로로 변했을 때 할 작업 (진입 시, 진입 후 화면 전환 시 모두 다)
     */
    protected abstract fun displayPortraitAfter()

    /**
     * onCreate의 모든 초기화 종료 후 추가 로직
     */
    protected abstract fun initAfterLogic()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreateView(inflater, container, savedInstanceState)

        // doCreate 전에 불림
        beforeCreateView()

        val view : View = doCreateView(inflater, container, savedInstanceState)!!

        // 5
        initLayout()

        // 6
        initData()

        // 7
        initAfterLogic()

        return view
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE ->
            {
                XELLogUtil.d_function(XELGlobalDefine.TAG, "onConfigurationChanged ORIENTATION_LANDSCAPE")

                displayLandscapeAfter()
            }

            Configuration.ORIENTATION_PORTRAIT ->
            {
                XELLogUtil.d_function(XELGlobalDefine.TAG, "onConfigurationChanged ORIENTATION_PORTRAITfr")

                displayPortraitAfter()
            }
        }
    }
}