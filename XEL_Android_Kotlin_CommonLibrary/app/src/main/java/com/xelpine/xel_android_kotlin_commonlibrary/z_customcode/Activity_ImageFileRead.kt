package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionCallback
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper.XELPermissionHelper
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception

class Activity_ImageFileRead : XELActivity_Base(){

    var imagefileread_iv_1: ImageView? = null

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_imagefileread)
    }

    override fun setTheme() {
    }

    override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.NONE
    }

    override fun setWindowTransitions() {}

    override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
        imagefileread_iv_1 = findViewById(R.id.imagefileread_iv_1) as ImageView
    }

    override fun initData() {}

    override fun displayLandscapeAfter() {}

    override fun displayPortraitAfter() {}

    override fun initAfterLogic() {
//        imagefileread_iv_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
        XELPermissionHelper.CheckPermission(
            this,
            this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            "????????? ???????????????.",
            callback
        )
    }

    /**
     * ?????? ?????? ??????
     */
    var callback: XELPermissionCallback = object : XELPermissionCallback{
        override fun PermissionPermitedAll(str_permissionName: Array<String?>?) {
            var str_result = "????????? ?????? ?????? \n"
            if (str_permissionName != null) {
                for (i in str_permissionName.indices) {
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "????????? ????????? - " + str_permissionName[i])
                    str_result = """
                        $str_result${str_permissionName[i]}
                        
                        """.trimIndent()
                }
            }

//            Toast.makeText(Activity_ImageFileRead.this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show();
            SelectImage()
        }

        override fun PermissionPermitedPart(str_permissionName: Array<String?>?) {}
        override fun PermissionDenied(str_permissionName: Array<String?>?) {
            var str_result = "????????? ?????? ?????? \n"
            if (str_permissionName != null) {
                for (i in str_permissionName.indices) {
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "????????? ????????? - " + str_permissionName[i])
                    str_result = """
                        $str_result${str_permissionName[i]}
                        
                        """.trimIndent()
                }
            }
        }

        override fun PermitDialogDenial() {
            XELLogUtil.e_function(XELGlobalDefine.TAG, "?????? ?????? ??????")
        }
    }


    /**
     * ????????? ?????? ??????
     */
    private fun SelectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
//                     ????????? ??????????????? ????????? ??????
                    val `in` = contentResolver.openInputStream(data?.data!!)
                    val img = BitmapFactory.decodeStream(`in`)
                    `in`!!.close()

                    // ????????? ??????
                    imagefileread_iv_1!!.setImageBitmap(img)

                    // ????????? ??????
                    val uri = saveImage(this, img, "KDY_FOLDER", "KDY_IMAGE")
                    XELLogUtil.i_function(XELGlobalDefine.TAG, "SaveImage uri : $uri")

                    // ????????? ?????? ???
//                    imagefileread_iv_1.setImageURI(data.getData());
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    /**
     * ????????? ??????.
     * ??????????????? 11 ?????? : ????????? ?????? ?????? ??????. MediaStore??? ?????? ?????????.
     * ??????????????? 10 ?????? : EXTERNAL_STORAGE ?????? ??????.
     * @param context
     * @param bitmap
     * @param folderName
     * @param fileName
     * @return
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun saveImage(
        context: Context,
        bitmap: Bitmap,
        folderName: String,
        fileName: String
    ): Uri? {
        var fos: OutputStream? = null
        var imageFile: File? = null
        var imageUri: Uri? = null
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val resolver = context.contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + folderName
                )
                imageUri =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                if (imageUri == null) throw IOException("Failed to create new MediaStore record.")
                fos = resolver.openOutputStream(imageUri)
            } else {
                val sdcard = Environment.getExternalStorageDirectory()
                val imagesDir = File(sdcard.absolutePath + File.separator + folderName)

//                File imagesDir = new File(Environment.getExternalStoragePublicDirectory(
//                        Environment.DIRECTORY_PICTURES).toString() + File.separator + folderName);
                if (!imagesDir.exists()) imagesDir.mkdir()
                imageFile = File(imagesDir, "$fileName.png")
                fos = FileOutputStream(imageFile)
            }
            if (!bitmap.compress(
                    Bitmap.CompressFormat.PNG,
                    100,
                    fos
                )
            ) throw IOException("Failed to save bitmap.")
            fos!!.flush()
        } finally {
            fos?.close()
        }
        if (imageFile != null) { //pre Q
            MediaScannerConnection.scanFile(context, arrayOf(imageFile.toString()), null, null)
            imageUri = Uri.fromFile(imageFile)
        }
        return imageUri
    }


    override fun doPause() {}

    override fun doResume() {}

    override fun doStart() {
    }

    override fun doStop() {
    }

    override fun doDestroy() {}
}