package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.core.content.ContextCompat;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class XELFileUtil
{
    /**
     * 파일 읽기 성공 시 인터페이스
     */
    public interface FileReadDataInterface
    {
        // 파일 읽기 성공
        void ReadFileSuccess (int Tag, String fileData);

        void ReadFileFailed (int Tag);
    }

    /**
     * 파일 읽기
     * @param directory
     * @param fileName
     */
    public static boolean ReadFromFile (Context context, String directory, String fileName, int tag, FileReadDataInterface fileReadDataInterface)
    {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "ReadFromFile (파일 읽기)");

        // 마운트 상태 확인
        String state = Environment.getExternalStorageState();

        // 마운트 또는 읽기 전용 마운트인 경우 읽기 가능
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            // SDK 23 이상
            if (SDK_INT >= 23)
            {
                if (checkPermission(context))
                {
                    File sdcard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdcard.getAbsolutePath() + directory);

                    if(dir.exists()) {
                        File file = new File(dir, fileName);
                        FileOutputStream os = null;
                        StringBuilder text = new StringBuilder();

                        if (file.exists())
                        {
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String line;
                                while ((line = br.readLine()) != null) {
                                    text.append(line);
                                    text.append('\n');
                                }
                                br.close();

                                XELLogUtil.i_function(XELGlobalDefine.TAG, "(SDK 23 이상, 파일 존재 O, 읽기 성공) FILE DATA : " + text);

                                fileReadDataInterface.ReadFileSuccess(tag, text.toString());

                                return true;

                            } catch (IOException e) {
                                //You'll need to add proper error handling here
                                XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 파일 존재 O, 읽기 에러)");

                                fileReadDataInterface.ReadFileFailed(tag);

                                return false;
                            }
                        }
                        else
                        {
                            XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 파일 존재 X)");

                            fileReadDataInterface.ReadFileFailed(tag);

                            return false;
                        }


                    }
                    else
                    {
                        XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 디렉토리 존재 X)");

                        fileReadDataInterface.ReadFileFailed(tag);

                        return false;
                    }
                } else {
                    // 권한 없음
//                    requestPermission(); // Code for permission
                    XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 이상, 권한 없음)");

                    fileReadDataInterface.ReadFileFailed(tag);

                    return false;
                }
            }
            // SDK 23 미만
            else
            {
                File sdcard = Environment.getExternalStorageDirectory();
                File dir = new File(sdcard.getAbsolutePath() + directory);
                if(dir.exists()) {
                    File file = new File(dir, fileName);
                    FileOutputStream os = null;
                    StringBuilder text = new StringBuilder();

                    if (file.exists())
                    {
                        try {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = br.readLine()) != null) {
                                text.append(line);
                                text.append('\n');
                            }
                            br.close();
                        } catch (IOException e) {
                            //You'll need to add proper error handling here
                        }

                        XELLogUtil.i_function(XELGlobalDefine.TAG, "(SDK 23 미만, 파일 존재 O, 읽기 성공) FILE DATA : " + text);

                        fileReadDataInterface.ReadFileSuccess(tag, text.toString());

                        return true;
                    }
                    else
                    {
                        XELLogUtil.e_function(XELGlobalDefine.TAG, "(SDK 23 미만, 파일 존재 X)");

                        fileReadDataInterface.ReadFileFailed(tag);

                        return false;
                    }
                }
                else
                {
                    XELLogUtil.i_function(XELGlobalDefine.TAG, "(SDK 23 미만, 읽기 실패)");

                    fileReadDataInterface.ReadFileFailed(tag);

                    return false;
                }
            }
        }
        else
        {
            XELLogUtil.i_function(XELGlobalDefine.TAG, "readFromFile 에러 : 마운트되어있지 않음");

            fileReadDataInterface.ReadFileFailed(tag);

            return false;
        }
    }

    /**
     * 권한 체크. 안드로이드 11 이상과 미만에 대해 요구하는 권한이 다르다.
     * @return
     */
    private static boolean checkPermission(Context context) {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }


    /**
     * 내부 저장소에 폴더 만들기
     * @param cxt
     */
    public static void initInternalDir(Context cxt, String folderPath) {
        new File(folderPath).mkdirs();

        try {
            new File(getInternalTempDir(cxt) + "/.nomedia").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 내부 저장소 위치
     * @param cxt
     * @return
     */
    public static String getInternalTempDir(Context cxt)
    {
        return cxt.getFilesDir().getAbsolutePath()  + "/IMAGE/temp";
    }

    /**
     * 내부 저장소 폴더 지우기
     * @param dir
     */
    public static void removeInternalDir(String dir) {
        File files = new File(dir);
        File[] childFileList = files.listFiles();
        if (files.exists()) {
            for (File childFile : childFileList) {
                if (childFile.isDirectory()) {
                    removeInternalDir(childFile.getAbsolutePath());
                } else {
                    childFile.delete();
                }
            }
            files.delete();
        }
    }

    /**
     * 외부 저장소에 폴더 만들기
     * @param cxt
     */
    public static void initExternalDir(Context cxt, String folderPath) {
        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + folderPath).mkdirs();

        try {
            new File(Environment.getExternalStorageDirectory().getAbsolutePath() + folderPath + "/.nomedia").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
