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
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.SkeletonSampleDataDto
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.SkeletonInterface
import java.io.File
import java.lang.Exception

/**
 * 뷰모델 - 자산 실사 헤더
 *
 * 화면에 뿌려질 정보들을 가진다.
 * 여기서 Model과의 통신을 통해 데이터를 주고받는다.
 * 최종적으로 이 값이 변경됨을 Activity (View) 가 감지하여 업데이트 되도록 한다.
 */
class ViewModel_Skeleton (
    application: Application,
    var context : Context,
    var activity: AppCompatActivity,
    var viewInterface: SkeletonInterface) : AndroidViewModel(application)
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
    var skeletonList : MutableLiveData<ArrayList<SkeletonSampleDataDto>> = MutableLiveData(ArrayList())
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
    fun CommonDataInfoInit (callback: CommonDataLoadCallback)
    {
        try {

            skeletonList.value?.clear()

            for (i in 0 .. 20)
            {
                var skeletonDto : SkeletonSampleDataDto = SkeletonSampleDataDto("Title $i", "Desc $i")

                skeletonList.value?.add(skeletonDto)
            }

            callback.CommonDataLoadSuccess()
        }
        catch (e : Exception)
        {

            callback.CommonDataLoadFailed()
        }
    }
//
//    fun AssetDataInfoInit (callback: FileLoadCallback)
//    {
//        if (XELGlobalDefine.TEST_MODE)
//        {
//            assetDataInfo.value?.code?.value = "하단_자산코드"
//            assetDataInfo.value?.name?.value = "하단_자산명칭"
//            assetDataInfo.value?.dept?.value = "하단_관리부서"
//            assetDataInfo.value?.allAssetQty?.value = 100.0
//            assetDataInfo.value?.installLocation?.value = "하단_설치장소"
//            assetDataInfo.value?.dueDiligenceLocation?.value = CommonLocation("TEST_CODE_1", "하단_실사장소")
//            assetDataInfo.value?.assetStatus?.value = CommonAssetStatus("TEST_CODE_2", "하단_자산상태")
//            assetDataInfo.value?.description?.value = "하단_비고"
//
//            callback.FileLoadSuccess()
//        }
//        else
//        {
//            callback.FileLoadError("[자산 실사 - 라인] 파일 로드 에러")
//        }
//    }
//
//
//    fun setSelectedLocation (selectedLocation: CommonLocation)
//    {
//        this.selectedLocation.value = selectedLocation
//    }
//
//    fun setSelectedAssetStatus (selectedAssetStatus: CommonAssetStatus)
//    {
//        this.selectedAssetStatus.value = selectedAssetStatus
//    }
//
//
//    // 설치장소 선택
//    fun OnClickLocation (view : View)
//    {
//        viewInterface.OnClickBtnLocation()
//    }
//
//    // 자산상태 선택
//    fun OnClickAssetStatus (view : View)
//    {
//        viewInterface.OnClickBtnAssetStatus()
//    }
//
//    // 사진 추가
//    fun OnClickTakePicture (view : View)
//    {
//        // take a photo
//        val fileName = "Pic_" + System.currentTimeMillis() + ".jpg"
//        val file: File = File(
//            XELFileUtil.getInternalTempDir(context)
//                .toString() + "/" + fileName
//        )
//        //Uri imageUri = Uri.fromFile(file);
//        val privateID: String = context.getPackageName()
//
//        //imageUri = FileProvider.getUriForFile(MainActivity.this, "com.douzone.mobile.${applicationId}.Androiddzcontainer", file);
//        imageUri = FileProvider.getUriForFile(
//            context,
//            privateID,
//            file
//        )
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
//        activity.startActivityForResult(intent, Activity_AssetDueDiligenceLine.Companion.TAG_TAKE_CAMERA)
//    }
//
//    // 저장
//    fun OnClickSave (view : View)
//    {
//        if (null != imageUri)
//        {
//            val strArr = imageUri.toString().split("/").toTypedArray()
//
//            Glide.with(context)
//                .asBitmap()
//                .load(imageUri) //                                .override(1024, 768)
//                .into(object : CustomTarget<Bitmap?>() {
//                    override fun onResourceReady(
//                        resource: Bitmap,
//                        transition: Transition<in Bitmap?>?
//                    ) {
//                        // 파일 저장
//                        BitmapUtil.SaveBitmapToFileCache(
//                            resource,
//                            Environment.getExternalStorageDirectory().absolutePath + "/" + AppCommonData.FILE_DIRECTORY_NAME,
//                            strArr[strArr.size - 1]
//                        )
//                    }
//
//                    override fun onLoadCleared(placeholder: Drawable?) {}
//                })
//
//        }
//        else {
//            viewInterface.ViewPictureisNothing("촬영된 이미지가 없습니다.")
//        }
//    }
}