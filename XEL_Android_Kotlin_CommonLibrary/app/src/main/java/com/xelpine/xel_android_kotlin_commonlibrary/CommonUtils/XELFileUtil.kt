package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Environment
import androidx.core.content.ContextCompat
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import java.io.*
import java.lang.StringBuilder

object XELFileUtil {
    /**
     * 파일 읽기
     * @param directory
     * @param fileName
     */
    fun ReadFromFile(
        context: Context,
        directory: String,
        fileName: String?,
        tag: Int,
        fileReadDataInterface: FileReadDataInterface
    ): Boolean {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "ReadFromFile (파일 읽기)")

        // 마운트 상태 확인
        val state = Environment.getExternalStorageState()

        // 마운트 또는 읽기 전용 마운트인 경우 읽기 가능
        return if (Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state) {
            // SDK 23 이상
            if (VERSION.SDK_INT >= 23) {
                if (checkPermission(context)) {
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
                                fileReadDataInterface.ReadFileSuccess(tag, text.toString())
                                true
                            } catch (e: IOException) {
                                //You'll need to add proper error handling here
                                XELLogUtil.e_function(
                                    XELGlobalDefine.TAG,
                                    "(SDK 23 이상, 파일 존재 O, 읽기 에러)"
                                )
                                fileReadDataInterface.ReadFileFailed(tag)
                                false
                            }
                        } else {
                            XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 파일 존재 X)")
                            fileReadDataInterface.ReadFileFailed(tag)
                            false
                        }
                    } else {
                        XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 디렉토리 존재 X)")
                        fileReadDataInterface.ReadFileFailed(tag)
                        false
                    }
                } else {
                    // 권한 없음
                    //                    requestPermission(); // Code for permission
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 권한 없음)")
                    fileReadDataInterface.ReadFileFailed(tag)
                    false
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
                        fileReadDataInterface.ReadFileSuccess(tag, text.toString())
                        true
                    } else {
                        XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 미만, 파일 존재 X)")
                        fileReadDataInterface.ReadFileFailed(tag)
                        false
                    }
                } else {
                    XELLogUtil.i_function(XELGlobalDefine.TAG, "(SDK 23 미만, 읽기 실패)")
                    fileReadDataInterface.ReadFileFailed(tag)
                    false
                }
            }
        } else {
            XELLogUtil.i_function(XELGlobalDefine.TAG, "readFromFile 에러 : 마운트되어있지 않음")
            fileReadDataInterface.ReadFileFailed(tag)
            false
        }
    }

    /**
     * 권한 체크. 안드로이드 11 이상과 미만에 대해 요구하는 권한이 다르다.
     * @return
     */
    private fun checkPermission(context: Context): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result =
                ContextCompat.checkSelfPermission(context, permission.READ_EXTERNAL_STORAGE)
            val result1 =
                ContextCompat.checkSelfPermission(context, permission.WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * 내부 저장소에 폴더 만들기
     * @param cxt
     */
    fun initInternalDir(cxt: Context, folderPath: String?) {
        File(folderPath).mkdirs()
        try {
            File(getInternalTempDir(cxt) + "/.nomedia").createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 내부 저장소 위치
     * @param cxt
     * @return
     */
    fun getInternalTempDir(cxt: Context): String {
        return cxt.filesDir.absolutePath + "/IMAGE/temp"
    }

    /**
     * 내부 저장소 폴더 지우기
     * @param dir
     */
    fun removeInternalDir(dir: String?) {
        val files = File(dir)
        val childFileList = files.listFiles()
        if (files.exists()) {
            for (childFile in childFileList) {
                if (childFile.isDirectory) {
                    removeInternalDir(childFile.absolutePath)
                } else {
                    childFile.delete()
                }
            }
            files.delete()
        }
    }

    /**
     * 외부 저장소에 폴더 만들기
     * @param cxt
     */
    fun initExternalDir(cxt: Context?, folderPath: String) {
        File(Environment.getExternalStorageDirectory().absolutePath + folderPath).mkdirs()
        try {
            File(Environment.getExternalStorageDirectory().absolutePath + folderPath + "/.nomedia").createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 파일 읽기 성공 시 인터페이스
     */
    interface FileReadDataInterface {
        // 파일 읽기 성공
        fun ReadFileSuccess(Tag: Int, fileData: String)
        fun ReadFileFailed(Tag: Int)
    }
}
