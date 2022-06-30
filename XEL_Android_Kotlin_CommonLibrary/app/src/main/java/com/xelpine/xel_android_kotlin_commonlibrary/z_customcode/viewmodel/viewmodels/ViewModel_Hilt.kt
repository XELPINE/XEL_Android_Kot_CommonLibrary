package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.HiltInterface

/**
 * 뷰모델 - 자산 실사 헤더
 *
 * 화면에 뿌려질 정보들을 가진다.
 * 여기서 Model과의 통신을 통해 데이터를 주고받는다.
 * 최종적으로 이 값이 변경됨을 Activity (View) 가 감지하여 업데이트 되도록 한다.
 */
class ViewModel_Hilt (
    application: Application,
    var viewInterface: HiltInterface) : AndroidViewModel(application)
{
    fun viewModelInit ()
    {
    }
}