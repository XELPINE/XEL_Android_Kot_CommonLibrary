package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication

import android.app.Application
import android.content.Context
import android.nfc.NfcAdapter
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELNotificationUtil.createNotificationChannel
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class XELGlobalApplication : Application()
{
    companion object
    {
        lateinit var instance: XELGlobalApplication

        // Components
        var globalContext: Context? = null
        private var mVolleyRequestQueue: RequestQueue? = null
        private var globalPreference: XELCommonShPreferences? = null
        private var volleyDefaultRetryPolicy: DefaultRetryPolicy? = null

        // NFC
        private var gNfcAdapter: NfcAdapter? = null

        fun initGlobalContext(context: Context) {
            globalContext = context.applicationContext
        }
    }



    override fun onCreate()
    {
        super.onCreate()
        setInstance(this)
        initGlobalContext(this)
        getSharedPreference(this)
        VolleyDefaultRetryPolicy()
        createNotificationChannel(this)
    }

    @Synchronized
    fun setInstance(mInstance: XELGlobalApplication) {
        instance = mInstance
    }

    @Synchronized
    fun getSharedPreference(context: Context): XELCommonShPreferences? {
        if (globalPreference == null) {
            globalPreference = XELCommonShPreferences(context)
        }
        return globalPreference
    }

    fun getNfcAdapter(cxt: Context?): NfcAdapter? {
        if (null == gNfcAdapter) {
            gNfcAdapter = NfcAdapter.getDefaultAdapter(cxt)
        }
        return gNfcAdapter
    }

    /**
     * Volley
     * @return
     */
    val volleyRequestQueue: RequestQueue?
        get() {
            if (mVolleyRequestQueue == null) {
                mVolleyRequestQueue = Volley.newRequestQueue(this.applicationContext)
            }
            return mVolleyRequestQueue
        }

    /**
     * Volley
     * @param req
     * @param <T>
    </T> */
    fun <T> addToVolleyRequestQueue(req: Request<T>?) {
        volleyRequestQueue!!.add(req)
    }

    /**
     * Volley??? ????????? ??????
     * @return
     */
    fun VolleyDefaultRetryPolicy(): DefaultRetryPolicy? {
        if (volleyDefaultRetryPolicy == null) {
            volleyDefaultRetryPolicy = DefaultRetryPolicy(
                30000,  // ???????????? ??????
                0,  // ????????? ??????
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )
        }
        return volleyDefaultRetryPolicy
    }


}