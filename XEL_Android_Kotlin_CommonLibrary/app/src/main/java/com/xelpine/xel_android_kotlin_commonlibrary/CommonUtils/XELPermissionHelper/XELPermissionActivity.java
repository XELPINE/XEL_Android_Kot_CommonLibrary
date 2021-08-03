package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil;

import java.util.ArrayList;

/**
 * 권한 요청 액티비티
 * 마지막 업데이트 : 2021-07-15
 */
public class XELPermissionActivity extends XELActivity_Base
{
    String[] str_permission = {};
    String str_informationData;
    int int_denied_permission_index = -1;

    @Override
    protected void doCreate(Bundle savedInstanceState) {
        super.doCreate(savedInstanceState);

        XELLogUtil.d_function(XELGlobalDefine.TAG, "onCreate");

        Intent getIntent = getIntent();

        // 퍼미션 수신
        str_permission = getIntent.getStringArrayExtra(XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUEST_PERMISSION);
        str_informationData = getIntent.getStringExtra(XELPermissionHelper.XELPINE_PERMISSIONHELPER_INFORMATION_DATA);


        // 모든 권한 클리어 여부 확인
        for (int i=0; i<str_permission.length; i++)
        {
            // 권한 체크
            int permissionCheck = ContextCompat.checkSelfPermission(this, str_permission[i]);

            // 하나라도 거부된 것이 있다면 이 루틴 통과.
            if (permissionCheck == PackageManager.PERMISSION_DENIED)
            {
                int_denied_permission_index = i;
                break;
            }

            // 마지막 권한까지 모두 허용
            if (i == str_permission.length-1)
            {
                Log.e("15", "권한 모두 존재 - 액티비티 종료");

                PermitAll(str_permission);
                finish();

                return;
            }
        }



        // 이 권한을 필요한 이유를 설명해야하는가?
        // 사용자가 이 페이지를 처음보거나 다시 묻지 않음을 선택 시 -> false
        // 명시적으로 거부한 경우 -> true
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, str_permission[int_denied_permission_index]))
        {
            XELLogUtil.e_function(XELGlobalDefine.TAG, "shouldShowRequestPermissionRationale TRUE -> 권한 없음 - 권한 설명");




            // 권한 요청 다이얼로그
            AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
            alt_bld.setMessage(str_informationData).setCancelable(false)
                    .setPositiveButton("요청", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            // 권한 요청
                            ActivityCompat.requestPermissions(
                                    XELPermissionActivity.this,
                                    str_permission,
                                    XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("거부", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();

                            DialogDenial();

                            finish();
                        }
                    });

            AppCompatDialog alert = alt_bld.create();
            alert.setTitle("경고");
            alert.show();

        }
        // 설명할 필요가 없다면
        else
        {
            XELLogUtil.e_function(XELGlobalDefine.TAG, "shouldShowRequestPermissionRationale FALSE -> 권한 없음 - 권한 요청");

            // 0.1초 뒤 시작
            XELHandlerUtil.PostDelayed(100, new XELHandlerUtil.DelayedCompleteCallback() {
                @Override
                public void DelayComplete() {

                    // 권한 요청
                    ActivityCompat.requestPermissions(
                            XELPermissionActivity.this,
                            str_permission,
                            XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE);

                }
            });


        }
    }

    @Override
    protected PresetAnimation setPresetAnimation() {
        return PresetAnimation.NONE;
    }

    @Override
    protected void setWindowTransitions() {
//        Fade fade = new Fade();
//        fade.setDuration(500);
//        fade.setMode(Visibility.MODE_OUT);
//        getWindow().setReturnTransition(fade);
    }

    @Override
    protected NFCReadMode setNFCReadMode() {
        return NFCReadMode.NONE;
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void DisplayLandscapeAfter() {

    }

    @Override
    protected void DisplayPortraitAfter() {

    }

    @Override
    protected void initAfterLogic() {

    }

    @Override
    protected void doPause() {

    }

    @Override
    protected void doResume() {

    }

    @Override
    protected void doDestroy() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> strArray_permitList = new ArrayList<>();
        ArrayList<String> strArray_denyList = new ArrayList<>();

        switch (requestCode) {
            case XELPermissionHelper.XELPINE_PERMISSIONHELPER_REQUESTCODE:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];

                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        // 권한 허가
                        // 해당 권한을 사용해서 작업을 진행할 수 있습니다

                        XELLogUtil.e_function(XELGlobalDefine.TAG, "onRequestPermissionsResult 권한 허가 - " + permission);


                        strArray_permitList.add(permission);
                    } else {
                        // 권한 거부
                        // 사용자가 해당권한을 거부했을때 해주어야 할 동작을 수행합니다

                        XELLogUtil.e_function(XELGlobalDefine.TAG, "onRequestPermissionsResult 권한 거부 - " + permission);

                        strArray_denyList.add(permission);
                    }
                }


                String[] permitArr = new String[strArray_permitList.size()];
                if (permitArr.length > 0) {
                    permitArr = strArray_permitList.toArray(permitArr);

                    if (permitArr.length == str_permission.length)
                    {
                        PermitAll(permitArr);
                    }
                    else
                    {
                        PermitPart(permitArr);
                    }
                }


                String[] denyArr = new String[strArray_denyList.size()];
                if (denyArr.length > 0) {
                    denyArr = strArray_denyList.toArray(denyArr);
                    Denied(denyArr);
                }


                finish();
                return;
        }
    }

    public void PermitAll (String[] str_permission)
    {
        XELPermissionHelper.XELPermissionCallback.PermissionPermitedAll(str_permission);
    }

    public void PermitPart (String[] str_permission)
    {
        XELPermissionHelper.XELPermissionCallback.PermissionPermitedPart(str_permission);
    }

    public void Denied (String[] str_permission)
    {
        XELPermissionHelper.XELPermissionCallback.PermissionDenied(str_permission);
    }

    public void DialogDenial ()
    {
        XELPermissionHelper.XELPermissionCallback.PermitDialogDenial();
    }
}
