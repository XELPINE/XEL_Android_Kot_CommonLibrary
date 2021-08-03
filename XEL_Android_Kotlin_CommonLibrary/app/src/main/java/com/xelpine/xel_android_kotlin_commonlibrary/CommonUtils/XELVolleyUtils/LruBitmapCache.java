package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * LruBitmapCache.
 * 내부 저장소에 이미지를 캐시로 두기 위한 클래스다.
 * 이 클래스를 사용할 경우 반드시 앱을 System.exit(0); 으로 종료해야만 재실행에도 정상동작한다.
 * 이유는 불명...
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache
{
 
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }
 
    public LruBitmapCache(Context ctx) {
        this(getCacheSize(ctx));
    }
 
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }
 
    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }
 
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
 
    // Returns a cache size equal to approximately three screens worth of images.
    public static int getCacheSize(Context ctx)
    {
        final DisplayMetrics displayMetrics = ctx.getResources().
                getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        // 4 bytes per pixel
        final int screenBytes = screenWidth * screenHeight * 4;
 
        return screenBytes * 3;
    }
}
