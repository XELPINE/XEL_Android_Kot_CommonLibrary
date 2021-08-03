package com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode

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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionCallback
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionHelper
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class Activity_FileWrite : XELActivity_Base() {

    var activity_fileWrite_et_writeText: TextInputEditText? = null
    var activity_fileWrite_btn_writeStart: MaterialButton? = null

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_filewrite)
    }

    override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.SLIDE_BOTTOM
    }

    override fun setWindowTransitions() {}

    override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
        activity_fileWrite_et_writeText =
            findViewById(R.id.activity_fileWrite_et_writeText) as TextInputEditText
        activity_fileWrite_btn_writeStart =
            findViewById(R.id.activity_fileWrite_btn_writeStart) as MaterialButton
        activity_fileWrite_btn_writeStart!!.setOnClickListener { fileWrite() }
    }

    override fun initData() {}

    override fun DisplayLandscapeAfter() {}

    override fun DisplayPortraitAfter() {}

    override fun initAfterLogic() {}


    private fun fileWrite() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            //다운로드 폴더에 "file.txt" 이름으로 txt 파일 저장
            //Environment.DIRECTORY_DOWNLOADS - 기기의 기본 다운로드 폴더
            val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath,
                "file" + ".txt"
            )
            try {
                val fw = FileWriter(file, false)
                fw.write(activity_fileWrite_et_writeText!!.text.toString())
                fw.close()
                Toast.makeText(applicationContext, "WRITE OK", Toast.LENGTH_SHORT).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
        }
    }

    override fun doPause() {}

    override fun doResume() {}

    override fun doDestroy() {}

}