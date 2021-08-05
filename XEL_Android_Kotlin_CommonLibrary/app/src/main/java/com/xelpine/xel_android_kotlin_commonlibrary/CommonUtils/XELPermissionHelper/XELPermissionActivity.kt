package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper;

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import java.util.ArrayList

/**
 * 권한 요청 액티비티
 * 마지막 업데이트 : 2021-07-15
 */
class XELPermissionActivity : XELActivity_Base() {
    var str_permission: Array<String?>? = arrayOf()
    var str_informationData: String? = null
    var int_denied_permission_index = -1
    protected override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        XELLogUtil.d_function(XELGlobalDefine.TAG, "onCreate")
        val getIntent: Intent = getIntent()

        // 퍼미션 수신
        str_permission =
            getIntent.getStringArrayExtra(XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION)
        str_informationData =
            getIntent.getStringExtra(XELPermissionHelper.XELPINE_PERMISSIONHELPER_INFORMATION_DATA)


        // 모든 권한 클리어 여부 확인
        for (i in str_permission!!.indices) {
            // 권한 체크
            val permissionCheck = ContextCompat.checkSelfPermission(
                this,
                str_permission!![i]!!
            )

            // 하나라도 거부된 것이 있다면 이 루틴 통과.
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                int_denied_permission_index = i
                break
            }

            // 마지막 권한까지 모두 허용
            if (i == str_permission!!.size - 1) {
                Log.e("15", "권한 모두 존재 - 액티비티 종료")
                PermitAll(str_permission)
                finish()
                return
            }
        }


        // 이 권한을 필요한 이유를 설명해야하는가?
        // 사용자가 이 페이지를 처음보거나 다시 묻지 않음을 선택 시 -> false
        // 명시적으로 거부한 경우 -> true
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                str_permission!![int_denied_permission_index]!!
            )
        ) {
            XELLogUtil.e_function(
                XELGlobalDefine.TAG,
                "shouldShowRequestPermissionRationale TRUE -> 권한 없음 - 권한 설명"
            )


            // 권한 요청 다이얼로그
            val alt_bld = AlertDialog.Builder(this)
            alt_bld.setMessage(str_informationData).setCancelable(false)
                .setPositiveButton("요청") { dialog, id -> // 권한 요청
                    ActivityCompat.requestPermissions(
                        this@XELPermissionActivity,
                        str_permission!!,
                        XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE
                    )
                    dialog.cancel()
                }
                .setNegativeButton(
                    "거부"
                ) { dialog, id ->
                    dialog.cancel()
                    DialogDenial()
                    finish()
                }
            val alert: AppCompatDialog = alt_bld.create()
            alert.setTitle("경고")
            alert.show()
        } else {
            XELLogUtil.e_function(
                XELGlobalDefine.TAG,
                "shouldShowRequestPermissionRationale FALSE -> 권한 없음 - 권한 요청"
            )

            // 0.1초 뒤 시작
            XELHandlerUtil.PostDelayed(100, object : XELHandlerUtil.DelayedCompleteCallback {
                override fun DelayComplete() {

                    // 권한 요청
                    ActivityCompat.requestPermissions(
                        this@XELPermissionActivity,
                        str_permission!!,
                        XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE
                    )
                }
            })
        }
    }

    protected override fun setPresetAnimation(): PresetAnimation {
        return PresetAnimation.NONE
    }

    protected override fun setWindowTransitions() {
//        Fade fade = new Fade();
//        fade.setDuration(500);
//        fade.setMode(Visibility.MODE_OUT);
//        getWindow().setReturnTransition(fade);
    }

    protected override fun setNFCReadMode(): NFCReadMode {
        return NFCReadMode.NONE
    }

    protected override fun initLayout() {}
    protected override fun initData() {}
    protected override fun DisplayLandscapeAfter() {}
    protected override fun DisplayPortraitAfter() {}
    protected override fun initAfterLogic() {}
    protected override fun doPause() {}
    protected override fun doResume() {}
    protected override fun doDestroy() {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val strArray_permitList = ArrayList<String>()
        val strArray_denyList = ArrayList<String>()
        when (requestCode) {
            XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE -> {
                var i = 0
                while (i < permissions.size) {
                    val permission = permissions[i]
                    if (grantResults.size > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        // 권한 허가
                        // 해당 권한을 사용해서 작업을 진행할 수 있습니다
                        XELLogUtil.e_function(
                            XELGlobalDefine.TAG,
                            "onRequestPermissionsResult 권한 허가 - $permission"
                        )
                        strArray_permitList.add(permission)
                    } else {
                        // 권한 거부
                        // 사용자가 해당권한을 거부했을때 해주어야 할 동작을 수행합니다
                        XELLogUtil.e_function(
                            XELGlobalDefine.TAG,
                            "onRequestPermissionsResult 권한 거부 - $permission"
                        )
                        strArray_denyList.add(permission)
                    }
                    i++
                }
                var permitArr = arrayOfNulls<String>(strArray_permitList.size)
                if (permitArr.size > 0) {
                    permitArr = strArray_permitList.toArray(permitArr)
                    if (permitArr.size == str_permission!!.size) {
                        PermitAll(permitArr)
                    } else {
                        PermitPart(permitArr)
                    }
                }
                var denyArr = arrayOfNulls<String>(strArray_denyList.size)
                if (denyArr.size > 0) {
                    denyArr = strArray_denyList.toArray(denyArr)
                    Denied(denyArr)
                }
                finish()
                return
            }
        }
    }

    fun PermitAll(str_permission: Array<String?>?) {
        XELPermissionHelper.XELPermissionCallback?.PermissionPermitedAll(str_permission)
    }

    fun PermitPart(str_permission: Array<String?>?) {
        XELPermissionHelper.XELPermissionCallback?.PermissionPermitedPart(str_permission)
    }

    fun Denied(str_permission: Array<String?>?) {
        XELPermissionHelper.XELPermissionCallback?.PermissionDenied(str_permission)
    }

    fun DialogDenial() {
        XELPermissionHelper.XELPermissionCallback?.PermitDialogDenial()
    }
}
