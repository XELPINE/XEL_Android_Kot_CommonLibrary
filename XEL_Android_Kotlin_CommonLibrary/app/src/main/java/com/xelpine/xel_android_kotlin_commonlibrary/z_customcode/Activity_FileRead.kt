package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionCallback
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionHelper
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class Activity_FileRead : XELActivity_Base ()
{
    protected override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_fileread)
    }

    override fun setTheme() {
    }

    protected override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.NONE
    }

    protected override fun setWindowTransitions() {}

    protected override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    protected override fun initLayout() {}

    protected override fun initData() {}

    protected override fun displayLandscapeAfter() {}

    protected override fun displayPortraitAfter() {}

    protected override fun initAfterLogic() {
        readFromFile("/", "XEL_Sample.txt")
    }


    /**
     * 파일 읽기
     * @param directory
     * @param fileName
     */
    private fun readFromFile(directory: String, fileName: String) {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "readFromFile")

        // 마운트 상태 확인
        val state = Environment.getExternalStorageState()

        // 마운트 또는 읽기 전용 마운트인 경우 읽기 가능
        if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
            // SDK 23 이상
            if (VERSION.SDK_INT >= 23) {
                if (checkPermission()) {
                    val sdcard = Environment.getExternalStorageDirectory()
                    val dir = File(sdcard.absolutePath + directory)
                    if (dir.exists()) {
                        val file = File(dir, fileName)
                        val os: FileOutputStream? = null
                        val text = StringBuilder()
                        if (file.exists()) {
                            try {
                                val br = BufferedReader(FileReader(file))
                                var line: String?
                                while (br.readLine().also { line = it } != null) {
                                    text.append(line)
                                    text.append('\n')
                                }
                                br.close()
                                XELLogUtil.i_function(
                                    XELGlobalDefine.TAG,
                                    "(SDK 23 이상, 파일 존재 O, 읽기 성공) FILE DATA : $text"
                                )
                            } catch (e: IOException) {
                                //You'll need to add proper error handling here
                                XELLogUtil.e_function(
                                    XELGlobalDefine.TAG,
                                    "(SDK 23 이상, 파일 존재 O, 읽기 에러)"
                                )
                            }
                        } else {
                            XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 파일 존재 X)")
                        }
                    }
                } else {
                    // 권한 없음
                    requestPermission() // Code for permission
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 권한 없음)")
                }
            } else {
                val sdcard = Environment.getExternalStorageDirectory()
                val dir = File(sdcard.absolutePath + directory)
                if (dir.exists()) {
                    val file = File(dir, fileName)
                    val os: FileOutputStream? = null
                    val text = StringBuilder()
                    if (file.exists()) {
                        try {
                            val br = BufferedReader(FileReader(file))
                            var line: String?
                            while (br.readLine().also { line = it } != null) {
                                text.append(line)
                                text.append('\n')
                            }
                            br.close()
                        } catch (e: IOException) {
                            //You'll need to add proper error handling here
                        }
                        XELLogUtil.i_function(
                            XELGlobalDefine.TAG,
                            "(SDK 23 미만, 파일 존재 O, 읽기 성공) FILE DATA : $text"
                        )
                    } else {
                        XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 미만, 파일 존재 X)")
                    }
                } else {
                    XELLogUtil.i_function(XELGlobalDefine.TAG, "(SDK 23 미만, 읽기 실패)")
                }
            }
        } else {
            XELLogUtil.i_function(XELGlobalDefine.TAG, "readFromFile 에러 : 마운트되어있지 않음")
        }
    }

    //    private boolean checkPermission() {
    //        int result = ContextCompat.checkSelfPermission(Activity_FileRead.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
    //        if (result == PackageManager.PERMISSION_GRANTED) {
    //            return true;
    //        } else {
    //            return false;
    //        }
    //    }
    //
    private val PERMISSION_REQUEST_CODE = 100
//
//    private void requestPermission() {
////        if (ActivityCompat.shouldShowRequestPermissionRationale(Activity_FileRead.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
////            Toast.makeText(Activity_FileRead.this, "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
////        } else {
//            ActivityCompat.requestPermissions(Activity_FileRead.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
////        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case PERMISSION_REQUEST_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.e("value", "Permission Granted, Now you can use local drive .");
//                } else {
//                    Log.e("value", "Permission Denied, You cannot use local drive .");
//                }
//                break;
//        }
//    }

    //
    //    private void requestPermission() {
    ////        if (ActivityCompat.shouldShowRequestPermissionRationale(Activity_FileRead.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
    ////            Toast.makeText(Activity_FileRead.this, "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
    ////        } else {
    //            ActivityCompat.requestPermissions(Activity_FileRead.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    ////        }
    //    }
    //
    //    @Override
    //    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    //        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    //        switch (requestCode) {
    //            case PERMISSION_REQUEST_CODE:
    //                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    //                    Log.e("value", "Permission Granted, Now you can use local drive .");
    //                } else {
    //                    Log.e("value", "Permission Denied, You cannot use local drive .");
    //                }
    //                break;
    //        }
    //    }
    /**
     * 권한 체크. 안드로이드 11 이상과 미만에 대해 요구하는 권한이 다르다.
     * @return
     */
    private fun checkPermission(): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result = ContextCompat.checkSelfPermission(
                this@Activity_FileRead,
                permission.READ_EXTERNAL_STORAGE
            )
            val result1 = ContextCompat.checkSelfPermission(
                this@Activity_FileRead,
                permission.WRITE_EXTERNAL_STORAGE
            )
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * 권한 요청. 안드로이드 11 이상과 미만에 대해 요구하는 권한이 다르다.
     * @return
     */
    private fun requestPermission() {
        // 안드로이드 11 이상 : 모든 파일에 접근 가능한 화면을 열어 요청해야 한다.
        if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data =
                    Uri.parse(
                        java.lang.String.format(
                            "package:%s",
                            getApplicationContext().getPackageName()
                        )
                    )
                startActivityForResult(intent, 2296)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 2296)
            }
        } else {
            //below android 11
//            ActivityCompat.requestPermissions(Activity_FileRead.this, new String[]{WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            XELPermissionHelper.CheckPermission(
                this,
                this, arrayOf(permission.WRITE_EXTERNAL_STORAGE),
                "권한을 요구합니다.",
                callback
            )
        }
    }

    /**
     * 권한 요청 콜백
     */
    var callback: XELPermissionCallback = object : XELPermissionCallback {
        override fun PermissionPermitedAll(str_permissionName: Array<String?>?) {
            var str_result = "퍼미션 허용 \n"

            if (str_permissionName != null) {
                for (i in str_permissionName.indices) {
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "퍼미션 허용됨 - " + str_permissionName[i])
                    str_result += "/" + str_permissionName[i]
                }
            }

//            relativeLayout_main.setBackgroundColor(Color.GREEN);
//            tv_result.setText(str_result);
            Toast.makeText(
                this@Activity_FileRead,
                "Allow permission for storage access!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun PermissionPermitedPart(str_permissionName: Array<String?>?) {}
        override fun PermissionDenied(str_permissionName: Array<String?>?) {
            var str_result = "퍼미션 거부 \n"

            if (str_permissionName != null)
            {
                for (i in str_permissionName.indices)
                {
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "퍼미션 거부됨 - " + str_permissionName[i])
                    str_result = """
                        $str_result${str_permissionName[i]}
                        
                        """.trimIndent()
                }
            }

//            relativeLayout_main.setBackgroundColor(Color.RED);
//            tv_result.setText(str_result);
        }

        override fun PermitDialogDenial() {
            XELLogUtil.e_function(XELGlobalDefine.TAG, "권한 요구 다이얼로그 거부")

//            tv_result.setText("권한 요구 다이얼로그 거부");
        }
    }

    /**
     * 권한 콜백. 안드로이드 11 이상의 ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION 권한 수신
     * @return
     */
    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2296) {
            if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    /**
     * 권한 콜백. 안드로이드 11 미만에 대한 요청 결과값
     * @return
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0) {
                val READ_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val WRITE_EXTERNAL_STORAGE = grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    protected override fun doPause() {}

    protected override fun doResume() {}
    override fun doStart() {
    }

    override fun doStop() {
    }

    protected override fun doDestroy() {}
}