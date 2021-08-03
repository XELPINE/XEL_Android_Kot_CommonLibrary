package com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode

import android.os.Bundle
import android.text.TextUtils
import android.transition.Slide
import android.view.Gravity
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.android.volley.Request
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils.XELVolleyUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.lang.Exception
import java.util.HashMap

class Activity_Volley : XELActivity_Base() {


    private var textView_tag_1_tag: TextView? = null
    private var textView_tag_1_data: TextView? = null
    private var textView_tag_2_tag: TextView? = null
    private var textView_tag_2_data: TextView? = null



    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)
    }

    override fun setPresetAnimation(): PresetAnimation {
        return PresetAnimation.NONE
    }

    override fun setWindowTransitions() {
        val slide = Slide()
        slide.duration = 300
        slide.slideEdge = Gravity.RIGHT
        slide.interpolator = FastOutSlowInInterpolator()
        window.enterTransition = slide

//        Explode explode = new Explode();
//        explode.setDuration(500);
//        getWindow().setEnterTransition(explode);

//        Fade fade = new Fade();
//        fade.setDuration(500);
//        fade.setMode(Visibility.MODE_OUT);
//        getWindow().setReturnTransition(fade);
    }

    override fun setNFCReadMode(): NFCReadMode {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
        textView_tag_1_tag = findViewById(R.id.textView_tag_1_tag) as TextView
        textView_tag_1_data = findViewById(R.id.textView_tag_1_data) as TextView
        textView_tag_2_tag = findViewById(R.id.textView_tag_2_tag) as TextView
        textView_tag_2_data = findViewById(R.id.textView_tag_2_data) as TextView
    }

    override fun initData() {}

    override fun DisplayLandscapeAfter() {}

    override fun DisplayPortraitAfter() {}

    override fun initAfterLogic() {

        /** StringRequest  */
        // URL
        val int_method = Request.Method.GET
        val url1 =
            "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=lphi2j7DdXEt39plDjx6Hazo%2BLN4NNqHft%2FVrOSFdy2uDUQunzCH7EZehMQaUPqnDCR3m5jV1yvWToknAp4L9A%3D%3D&mapX=127.02955&mapY=37.492335&radius=1000&pageNo=1&numOfRows=10&listYN=Y&arrange=A&MobileOS=AND&MobileApp=com.example.testvolley"
        val url2 =
            "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=perl&site=stackoverflow"
        val url3 = "http://iherb2.lgdacom.net/weather/digital.xml"
        val url4 = "https://httpstat.us/200?sleep=120000"


        // stringRequest 모듈. 아래의 정보를 그대로 던지면 콜백 핸들러로 정보들이 넘어온다.

        // GET
        // GET일 때는 hashmap 데이터를 null로 넘긴다.
        XELVolleyUtil.startStringRequestData(
            this,
            Request.Method.GET,
            url1,
            null,
            "TAG_1",
            "UTF-8",
            XELGlobalApplication.getInstance().VolleyDefaultRetryPolicy(),
            this
        )
        val map = HashMap<String, String>()
        map["testKey1"] = "testValue1"
        map["testKey2"] = "testValue2"

        // POST
        // POST는 항상 hashmap으로 전달할 데이터를 넘긴다.
        XELVolleyUtil.startStringRequestData(
            this,
            Request.Method.GET,
            url2,
            map,
            "TAG_2",
            "UTF-8",
            XELGlobalApplication.getInstance().VolleyDefaultRetryPolicy(),
            this
        )

        // (번외) 취소를 원하는 경우 태그를 이용해 취소하는 것이 좋다.
//		SingleTon.getInstance(this).getRequestQueue().cancelAll("TAG_2");
        /** StringRequest 끝  */
    }

    override fun onDataResponseSucess(tag: String, data: String) {
        super.onDataResponseSucess(tag, data)
        XELDialogUtil.closeWait(0)
        XELLogUtil.i_network(XELGlobalDefine.TAG, "TEST onDataResponseSucess")
        XELLogUtil.i_network(XELGlobalDefine.TAG, "MainActivity handler TAG = $tag")
        XELLogUtil.i_network(XELGlobalDefine.TAG, "MainActivity handler DATA = $data")
        if (TextUtils.equals(tag, "TAG_1")) {
            textView_tag_1_data!!.text = data
        } else if (TextUtils.equals(tag, "TAG_2")) {
            textView_tag_2_data!!.text = data
        }
    }

    override fun onDataResponseError(tag: String, errorCode: Int) {
        super.onDataResponseError(tag, errorCode)
        XELDialogUtil.closeWait(0)
        XELLogUtil.e_network(XELGlobalDefine.TAG, "TEST onDataResponseError")
        XELLogUtil.e_network(XELGlobalDefine.TAG, "MainActivity errorhandler TAG = $tag")
        XELLogUtil.e_network(
            XELGlobalDefine.TAG,
            "MainActivity errorhandler ERROR_CODE = $errorCode"
        )
        if (TextUtils.equals(tag, "TAG_1")) {
            textView_tag_1_data!!.text = errorCode.toString() + ""
        } else if (TextUtils.equals(tag, "TAG_2")) {
            textView_tag_2_data!!.text = errorCode.toString() + ""
        }
    }

    override fun onDataException(e: Exception?) {
        XELLogUtil.e(XELGlobalDefine.TAG, "===== TEST onDataException")
    }

    override fun doPause() {}
    override fun doResume() {}
    override fun doDestroy() {}
}
