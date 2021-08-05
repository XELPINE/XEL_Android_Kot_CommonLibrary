package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapUtil {
    /**
     * 비트맵을 생성한다.
     *
     * @param data 이미지 데이터
     * @return 비트맵 리턴
     */
    public static Bitmap createBitmap(byte[] data) {
        Bitmap bitmap = null;

        if (data != null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inPurgeable = true;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }

        return bitmap;
    }

    /**
     * 비트맵의 Width 를 조회한다.
     *
     * @param context  Application Context
     * @param fileName 파일명
     * @return Width 리턴
     */
    public static int getBitmapWidth(Context context, String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return 0;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);
            return options.outWidth;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Height 를 조회한다.
     *
     * @param context  Application Context
     * @param fileName 파일명
     * @return Height 리턴
     */
    public static int getBitmapHeight(Context context, String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return 0;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileName, options);
            return options.outHeight;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Width 를 조회한다.
     *
     * @param context    Application Context
     * @param resourceId 이미지 리소스 ID
     * @return Width 리턴
     */
    public static int getBitmapWidth(Context context, int resourceId) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), resourceId);
            return options.outWidth;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Height 를 조회한다.
     *
     * @param context    Application Context
     * @param resourceId 이미지 리소스 ID
     * @return Height 리턴
     */
    public static int getBitmapHeight(Context context, int resourceId) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), resourceId);
            return options.outHeight;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Width 를 조회한다.
     *
     * @param context Application Context
     * @param data    이미지 데이터
     * @return Width 리턴
     */
    public static int getBitmapWidth(Context context, byte[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length);
            return options.outWidth;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Height 를 조회한다.
     *
     * @param context Application Context
     * @param data    이미지 데이터
     * @return Height 리턴
     */
    public static int getBitmapHeight(Context context, byte[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length);
            return options.outHeight;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Width 를 조회한다.
     *
     * @param context     Application Context
     * @param inputStream 이미지 스트림
     * @return Width 리턴
     */
    public static int getBitmapWidth(Context context, InputStream inputStream) {
        try {
            if (inputStream == null || inputStream.available() == 0) {
                return 0;
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream);
            return options.outWidth;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵의 Height 를 조회한다.
     *
     * @param context     Application Context
     * @param inputStream 이미지 스트림
     * @return Height 리턴
     */
    public static int getBitmapHeight(Context context, InputStream inputStream) {
        try {
            if (inputStream == null || inputStream.available() == 0) {
                return 0;
            }

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream);
            return options.outHeight;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 비트맵을 기준 Width 길이에 맞춰서 Height 크기를 변경한다.
     *
     * @param bitmap         원본 비트맵
     * @param thresholdWidth 기준 Width 길이
     * @return 비트맵 리턴
     */
    public static Bitmap resizeBitmapByWidth(Bitmap bitmap, int thresholdWidth) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float rate = 0.0f;

        rate = thresholdWidth / (float) width;
        height = (int) (height * rate);
        width = thresholdWidth;
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    /**
     * 비트맵을 기준 Height 길이에 맞춰서 Width 크기를 변경한다.
     *
     * @param bitmap          원본 비트맵
     * @param thresholdHeight 기준 Height 길이
     * @return 비트맵 리턴
     */
    public static Bitmap resizeBitmapByHeight(Bitmap bitmap, int thresholdHeight) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float rate = 0.0f;

        rate = thresholdHeight / (float) height;
        width = (int) (width * rate);
        height = thresholdHeight;
        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    /**
     * 비트맵을 비율에 맞춰서 max값 만큼 크기를 변경한다.
     *
     * @param bitmap 원본 비트맵
     * @param max    원하는 크기의 값
     * @return 비트맵 리턴
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int max) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float rate = 0.0f;

        if (width > height) {
            rate = max / (float) width;
            height = (int) (height * rate);
            width = max;
        } else {
            rate = max / (float) height;
            width = (int) (width * rate);
            height = max;
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    /**
     * 비트맵을 ratio에 맞춰서 max값 만큼 크기를 변경한다.
     *
     * @param bitmap 원본 비트맵
     * @param max    변경 길이
     * @param isKeep 변경 길이보다 width 와 height 모두 작은 크기인 경우 유지 여부
     * @return 비트맵 리턴
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int max, boolean isKeep) {
        if (!isKeep) {
            return resizeBitmap(bitmap, max);
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float rate = 0.0f;

        if (width > height) {
            if (max > width) {
                rate = max / (float) width;
                height = (int) (height * rate);
                width = max;
            }
        } else {
            if (max > height) {
                rate = max / (float) height;
                width = (int) (width * rate);
                height = max;
            }
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true);
    }

    /**
     * 비트맵 이미지를 정사각형으로 만든다.
     *
     * @param bitmap 원본 비트맵
     * @param max    기준 길이
     * @return 비트맵 리턴
     */
    public static Bitmap resizeSquare(Bitmap bitmap, int max) {
        if (bitmap == null) {
            return null;
        }

        return Bitmap.createScaledBitmap(bitmap, max, max, true);
    }

    /**
     * 비트맵의 가운데를 기준으로 width, height 크기 만큼 자른다.
     *
     * @param bitmap 원본 비트맵
     * @param width  너비
     * @param height 높이
     * @return 비트맵 리턴
     */
    public static Bitmap cropCenterBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }

        int originWidth = bitmap.getWidth();
        int originHeight = bitmap.getHeight();

        if (originWidth < width && originHeight < height) {
            return bitmap;
        }

        int x = 0;
        int y = 0;

        if (originWidth > width) {
            x = (originWidth - width) / 2;
        }

        if (originHeight > height) {
            y = (originHeight - height) / 2;
        }

        int cropWidth = width;
        int cropHeiht = height;

        if (width > originWidth) {
            cropWidth = originWidth;
        }

        if (height > originHeight) {
            cropHeiht = originHeight;
        }

        return Bitmap.createBitmap(bitmap, x, y, cropWidth, cropHeiht);
    }

    /**
     * 비트맵을 회전시킨다.
     *
     * @param bitmap  원본 비트맵
     * @param degrees 회전 각도
     * @return 결과 리턴
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.setRotate(degrees, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);

            try {
                Bitmap tempBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                if (bitmap != tempBitmap) {
                    bitmap.recycle();
                    bitmap = tempBitmap;
                }
            } catch (OutOfMemoryError oome) {
                return bitmap;
            }
        }

        return bitmap;
    }

    /**
     * DP 를 Pixel 로 변환한다.
     *
     * @param context  Application Context
     * @param targetDP 변환 DP
     * @return 결과 리턴
     */
    public static int dpToPixel(Context context, float targetDP) {
        // 화면 Density 조회
        final float density = context.getResources().getDisplayMetrics().density;
        // Density Scale 을 기준으로 dp를 픽셀로 변환
        int resultPixel = (int) (targetDP * density + 0.5f);
        return resultPixel;
    }

    public static float pixelToDp(Context context, float px) {
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * 화면 Width 를 조회한다.
     *
     * @param context Application Context
     * @return Width 리턴
     */
    public static int getDisplayWidth(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    /**
     * 화면 Height 를 조회한다.
     *
     * @param context Application Context
     * @return Height 리턴
     */
    public static int getDisplayHeight(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, options.outWidth, options.outHeight);
        options.inSampleSize = 2;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 지정한 패스의 파일의 EXIF 정보를 읽어서 회전시킬 각도 구하기
     *
     * @return degree
     */
    public synchronized static int GetExifOrientation(String path) {
        int degree = 0;
        ExifInterface exif = null;

        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            Log.e("TAG", "cannot read exif");
            e.printStackTrace();
        }

        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);

            if (orientation != -1) {
                // We only recognize a subset of orientation tag values.
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }

        return degree;
    }

    /**
     * 지정한 패스의 파일을 EXIF 정보에 맞춰 회전시키기
     *
     * @param bitmap bitmap handle
     * @return Bitmap
     */
    public synchronized static Bitmap GetRotatedBitmap(Bitmap bitmap, int degrees) {
        if (degrees != 0 && bitmap != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                        bitmap.getHeight(), m, true);
                if (bitmap != b2) {
                    bitmap.recycle();
                    bitmap = b2;
                }
            } catch (OutOfMemoryError ex) {
                // We have no memory to rotate. Return the original bitmap.
            }
        }

        return bitmap;
    }

    public static Uri getUriFromPath(Context cxt, String filePath) {
        long photoId;
        Uri photoUri = MediaStore.Images.Media.getContentUri("external");
        String[] projection = {MediaStore.Images.ImageColumns._ID};
        Cursor cursor = cxt.getContentResolver().query(photoUri, projection, MediaStore.Images.ImageColumns.DATA + " LIKE ?", new String[] { filePath }, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(projection[0]);
        photoId = cursor.getLong(columnIndex);

        cursor.close();
        return Uri.parse(photoUri.toString() + "/" + photoId);
    }

    public static Bitmap getBitmapFromUri(Context cxt, Uri uri){
        Bitmap bitmap = null;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(cxt.getContentResolver(), uri));
            } else {
                bitmap = MediaStore.Images.Media.getBitmap(cxt.getContentResolver(), uri);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static boolean SaveBitmapToFileCache(Bitmap bitmap, String strFilePath, String filename)
    {
        XELLogUtil.Companion.d_function(XELGlobalDefine.TAG, "SaveBitmapToFileCache (이미지 저장) : " + filename);

        boolean isSuccess = false;

        File file = new File(strFilePath);

        // If no folders
        if (!file.exists()) {
            file.mkdirs();
            // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

        File fileCacheItem = new File(strFilePath + "/" + filename);
        if (file.exists())
            fileCacheItem.delete();
        OutputStream out = null;

        try {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            isSuccess = true;

            XELLogUtil.Companion.d_function(XELGlobalDefine.TAG, "SaveBitmapToFileCache (이미지 저장) : (성공)");
        } catch (Exception e) {
            e.printStackTrace();
            XELLogUtil.Companion.e(XELGlobalDefine.TAG, "SaveBitmapToFileCache (이미지 저장) : (실패)", e);
        } finally {
            try {
                out.close();

                return isSuccess;
            } catch (IOException e) {
                e.printStackTrace();

                return isSuccess;
            }
        }
    }

    public static Bitmap getBitmapFromPath(String strImagePath) {
        return BitmapFactory.decodeFile(strImagePath);
    }

    public static Bitmap getBmpResizeFromPath(String strImagePath) {

        boolean bChange = false; //변경하는가? 변경할 필요없는데 로테이트/리사이즈 할필요 없으니까.
        int nResizeValue = 512;
//		int nResizeValue = RESIZE_VALUE;
//		if(bThumnail) {
//			nResizeValue = RESIZE_VALUE_FOMTHUMBNAIL;
//		}
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strImagePath, options);

        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;

        int nInSampleSize = 1;
        // int nInSampleSize = Math.min(optWidth/RESIZE_VALUE, optHeight/RESIZE_VALUE);
        if( srcHeight > nResizeValue || srcWidth > nResizeValue){
            int heightRatio = Math.round((float)srcHeight / (float)nResizeValue);
            int widthRatio = Math.round((float)srcWidth / (float)nResizeValue);

            nInSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = nInSampleSize;
        if(nInSampleSize>1) {
            bChange = true; // 사이즈변경 필요.
        }

        options.inPurgeable = true;

        Bitmap optBmp = BitmapFactory.decodeFile(strImagePath, options);
        int optWidth = optBmp.getWidth();
        int optHeight = optBmp.getHeight();

        float scaleWidth = 0;
        float scaleHeight = 0;
        if(optWidth >= optHeight) {
            scaleWidth = ((float) nResizeValue) / optWidth;
            float ratio = ((float) optWidth) / nResizeValue;
            int newHeight = (int) (optHeight / ratio);
            scaleHeight = ((float) newHeight) / optHeight;
        }else {
            scaleHeight = ((float) nResizeValue) / optHeight;
            float ratio = ((float) optHeight) / nResizeValue;
            int newWidth = (int) (optWidth / ratio);
            scaleWidth = ((float) newWidth) / optWidth;
        }

        int nDegree = getRotateValueUseExif(strImagePath);
        Matrix matrix = new Matrix();
        if(nDegree != 0) {
            bChange = true;
            matrix.postRotate(nDegree);
        }
        if(scaleWidth != 1.0f || scaleHeight != 1.0f) {
            bChange = true;
            matrix.postScale(scaleWidth, scaleHeight);
        }

        if(bChange == false) { // 이미지를 수정할 필요없다.
            optBmp.recycle();
            return getBitmapFromPath(strImagePath);
        }

        return Bitmap.createBitmap(optBmp, 0, 0, optWidth, optHeight, matrix, false);
    }

    public static int getRotateValueUseExif(String strPath) {

        int nDegree = 0;
        try
        {
            ExifInterface exif = new ExifInterface(strPath);
            int orient = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch( orient )
            {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    nDegree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    nDegree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    nDegree = 270;
                    break;
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
//            Trace.e( "getRotateValueUseExif() IOException:" + e.getMessage());
        }

//        Trace.i( "getRotateValueUseExif() nDegree:" + nDegree + ", strPath:" + strPath);
        return nDegree;
    }
    public static int getOrientValueUseExif(String strPath) {
//        Trace.i( "getOrientValueUseExif() strPath:" + strPath);
        try
        {
            ExifInterface exif = new ExifInterface(strPath);
            return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        }
        catch( IOException e )
        {
//            Trace.e( "getOrientValueUseExif() IOException:" + e.getMessage());
        }

        return ExifInterface.ORIENTATION_UNDEFINED;
    }
}