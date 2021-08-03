package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication;


import android.app.Application;
import android.content.Context;
import android.nfc.NfcAdapter;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class XELGlobalApplication extends Application {

    static XELGlobalApplication mInstance = null;

    // Components
//    private DisplayImageOptions options;
    private static Context globalContext = null;
    private static RequestQueue mVolleyRequestQueue = null;
    private static XELCommonShPreferences globalPreference = null;
    private static DefaultRetryPolicy volleyDefaultRetryPolicy = null;

    // NFC
    private static NfcAdapter gNfcAdapter = null;

    @Override
    public void onCreate()
    {
        super.onCreate();

        setInstance(this);
        initGlobalContext(this);
//        initImageLoader();
        getSharedPreference(this);
        VolleyDefaultRetryPolicy();
    }

//    public DisplayImageOptions getDisplayOption() {
//
//        if (this.options == null) {
//            options = new DisplayImageOptions.Builder()
////                    .showImageOnLoading(R.drawable.trans)
////                    .showImageForEmptyUri(R.drawable.trans)
////                    .showImageOnFail(R.drawable.trans)
//                    .cacheInMemory(true)
//                    .cacheOnDisk(true)
//                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//                    .considerExifParams(true)
//                    .bitmapConfig(Bitmap.Config.RGB_565)
//                    .build();
//        }
//        return options;
//    }

    public synchronized void setInstance(XELGlobalApplication mInstance)
    {
        this.mInstance = mInstance;
    }

    public static XELGlobalApplication getInstance() {
        return mInstance;
    }

    public static void initGlobalContext (Context context)
    {
        globalContext = context.getApplicationContext();
    }

    public static Context getGlobalContext ()
    {
        return globalContext;
    }

    public static void setNfcAdapter (NfcAdapter nfcAdapter) {
        gNfcAdapter = nfcAdapter;
    }

    public NfcAdapter getNfcAdapter(Context cxt) {
        if (null == gNfcAdapter) {
            gNfcAdapter = NfcAdapter.getDefaultAdapter(cxt);
        }
        return gNfcAdapter;
    }

//    private void initImageLoader ()
//    {
//        // ImageLoader
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .memoryCacheSize(2 * 1024 * 1024)
//                .denyCacheImageMultipleSizesInMemory()
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .writeDebugLogs() // Remove for release app
//                .build();
//
//        // ImageLoader
//        ImageLoader.getInstance().init(config);
//    }

    /**
     * 프리퍼런스
     * @param context
     * @return
     */
    public static synchronized XELCommonShPreferences getSharedPreference (Context context)
    {
        if( globalPreference == null )
        {
            globalPreference = new XELCommonShPreferences(context);
        }
        return globalPreference;
    }

    /**
     * Volley
     * @return
     */
    public RequestQueue getVolleyRequestQueue()
    {
        if (mVolleyRequestQueue == null)
        {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mVolleyRequestQueue = Volley.newRequestQueue(this.getApplicationContext());
        }
        return mVolleyRequestQueue;
    }

    /**
     * Volley
     * @param req
     * @param <T>
     */
    public <T> void addToVolleyRequestQueue(Request<T> req) {
        getVolleyRequestQueue().add(req);
    }

    /**
     * Volley의 재접속 로직
     * @return
     */
    public DefaultRetryPolicy VolleyDefaultRetryPolicy()
    {
        if (volleyDefaultRetryPolicy == null)
        {
            volleyDefaultRetryPolicy = new DefaultRetryPolicy(
                    120000,   // 타임아웃 시간
                    0,  // 재시도 횟수
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        }

        return volleyDefaultRetryPolicy;
    }
}
