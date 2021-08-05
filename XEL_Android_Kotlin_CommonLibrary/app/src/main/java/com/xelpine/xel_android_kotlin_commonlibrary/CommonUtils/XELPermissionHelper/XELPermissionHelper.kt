package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper;

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

/**
 * 2021-07-08 : 전체 권한 허용 관련 인터페이스 추가
 */
object XELPermissionHelper {
    const val XELPINE_PERMISSIONHELPER_REQUESTCODE = 100
    const val XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION = "Request_Permission"
    const val XELPINE_PERMISSIONHELPER_INFORMATION_DATA = "Information_Data"
    var XELPermissionCallback: XELPermissionCallback? = null

    /**
     * 퍼미션 체크 시작.
     * @param context
     * @param str_permission
     * @param str_informationData
     * @param callback
     */
    fun CheckPermission(
        context: Context,
        activity: Activity?,
        str_permission: Array<String?>?,
        str_informationData: String?,
        callback: XELPermissionCallback
    ) {
        Log.d("15", "XelpinePermissionHelper CheckPermission")

        // 버전체크
        if (CheckAndroidOSVersionisMOver()) {
            XELPermissionCallback = callback
            val intent = Intent(context, XELPermissionActivity::class.java)
            intent.putExtra(XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION, str_permission)
            intent.putExtra(XELPINE_PERMISSIONHELPER_INFORMATION_DATA, str_informationData)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            // 애니메이션 추가
            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                activity
            )
            context.startActivity(intent, transitionActivityOptions.toBundle())

//            context.startActivity(intent);
        } else {
            callback.PermissionPermitedAll(str_permission)
        }
    }

    /**
     * Android OS 버전 체크. 마쉬멜로우 이상인지를 판단한다. 이상이라면 true. 아니라면 false.
     */
    fun CheckAndroidOSVersionisMOver(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            true
        } else {
            false
        }
    }
}
