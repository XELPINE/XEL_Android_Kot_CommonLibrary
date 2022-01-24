package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.callback.CommonDataLoadCallback
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.FragmentInterface
import java.io.File
import java.lang.Exception

/**
 * 뷰모델 - 자산 실사 헤더
 *
 * 화면에 뿌려질 정보들을 가진다.
 * 여기서 Model과의 통신을 통해 데이터를 주고받는다.
 * 최종적으로 이 값이 변경됨을 Activity (View) 가 감지하여 업데이트 되도록 한다.
 */
class ViewModel_Fragment (
    application: Application,
    var context : Context,
    var activity: AppCompatActivity,
    var viewInterface: FragmentInterface
) : AndroidViewModel(application)
{
//    // 자산 실사 라인 기본 정보 데이터 클래스
//    inner class CommonDataInfo(
//        var location : MutableLiveData<CommonLocation?>,    // 설치장소
//        var dept : MutableLiveData<CommonDept?>,    // 실사부서
//        var allAssetQty : MutableLiveData<Double?>,    // 대상 자산수
//        var processAssetQty : MutableLiveData<Double?>,    // 실사 자산수
//        var notStartAssetQty : MutableLiveData<Double?>    // 미 실사수
//    )
//    {
//        constructor() : this (MutableLiveData(null), MutableLiveData(null), MutableLiveData(null), MutableLiveData(null), MutableLiveData(null))
//    }
//
//    // 자산 실사 라인 자산 정보 데이터 클래스
//    inner class AssetDataInfo(
//        var code : MutableLiveData<String?>,    // 자산코드
//        var name : MutableLiveData<String?>,    // 자산명칭
//        var dept : MutableLiveData<String?>,    // 관리부서
//        var allAssetQty : MutableLiveData<Double?>,    // 취득수량
//        var installLocation : MutableLiveData<String?>,    // 설치장소
//        var dueDiligenceLocation : MutableLiveData<CommonLocation?>,    // 실사장소
//        var assetStatus: MutableLiveData<CommonAssetStatus?>,    // 자산상태
//        var description : MutableLiveData<String?>    // 비고
//    )
//    {
//        constructor() : this (MutableLiveData(null), MutableLiveData(null), MutableLiveData(null),MutableLiveData(null), MutableLiveData(null), MutableLiveData(null), MutableLiveData(null), MutableLiveData(null))
//    }
//
//    var FragmentList : MutableLiveData<ArrayList<FragmentSampleDataDto>> = MutableLiveData(ArrayList())
//
//    @JvmField
//    var selectedLocation : MutableLiveData<CommonLocation> = MutableLiveData()
//    @JvmField
//    var selectedAssetStatus : MutableLiveData<CommonAssetStatus> = MutableLiveData()
//
//
//
//
//
//
//    fun CommonDataInfoInit (callback: CommonDataLoadCallback)
//    {
//        try {
//
//            FragmentList.value?.clear()
//
//            for (i in 0 .. 20)
//            {
//                var FragmentDto : FragmentSampleDataDto = FragmentSampleDataDto("Title $i", "Desc $i")
//
//                FragmentList.value?.add(FragmentDto)
//            }
//
//            callback.CommonDataLoadSuccess()
//        }
//        catch (e : Exception)
//        {
//
//            callback.CommonDataLoadFailed()
//        }
//    }
}