package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * 2021-07-08 : 전체 권한 허용 관련 인터페이스 추가
 */
public class XELPermissionHelper
{
    public static final int XELPINE_PERMISSIONHELPER_REQUESTCODE = 100;
    public static final String XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION = "Request_Permission";
    public static final String XELPINE_PERMISSIONHELPER_INFORMATION_DATA = "Information_Data";
    public static XELPermissionCallback XELPermissionCallback;


    /**
     * 퍼미션 체크 시작.
     * @param context
     * @param str_permission
     * @param str_informationData
     * @param callback
     */
    public static void CheckPermission (Context context, Activity activity, String[] str_permission, String str_informationData, XELPermissionCallback callback)
    {
        Log.d("15", "XelpinePermissionHelper CheckPermission");

        // 버전체크
        if (CheckAndroidOSVersionisMOver())
        {
            XELPermissionCallback = callback;

            Intent intent = new Intent(context, XELPermissionActivity.class);

            intent.putExtra(XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION, str_permission);
            intent.putExtra(XELPINE_PERMISSIONHELPER_INFORMATION_DATA, str_informationData);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // 애니메이션 추가
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    activity);

            context.startActivity(intent, transitionActivityOptions.toBundle());

//            context.startActivity(intent);
        }
        //버전이 마쉬멜로우보다 아래라면 그냥 true.
        else
        {
            callback.PermissionPermitedAll (str_permission);
        }

    }

    /**
     * Android OS 버전 체크. 마쉬멜로우 이상인지를 판단한다. 이상이라면 true. 아니라면 false.
     */
    public static boolean CheckAndroidOSVersionisMOver ()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
