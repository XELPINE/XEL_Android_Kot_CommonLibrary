package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils;

import android.os.Bundle;
import android.os.Message;

import com.android.volley.Response;

/**
 * Volley의 StringRequest 중 Listner의 확장 클래스. (정상동작 시 불리는 리스너)
 *
 * 인스턴스 생성 후
 * setHandler로 콜백 핸들러 설정,
 * setCharsetStringRequest로 StringRequsetwithCharset을 지정해야 한다.
 *
 * handler로 결과값이 전달되며 에러코드는 아래에 있다.
 */
public class Volley_Response_StringListener implements Response.Listener
{
    XELVolleyUtil.XELVolleyResponseInterface callbackHandler;    // 콜백 핸들러
    StringRequest_withCharset csr;

    // 결과값 key
    public final static String TAG = "tag";
    public final static String DATA = "data";

    /**
     * 콜백 핸들러 세팅.
     * @param handler
     */
    public void setHandler (XELVolleyUtil.XELVolleyResponseInterface handler)
    {
        this.callbackHandler = handler;
    }

    /**
     * StringRequset_withCharset 변수를 전달한다. TAG값을 얻기 위함.
     * @param csr
     */
    public void setCharsetStringRequest (StringRequest_withCharset csr)
    {
        this.csr = csr;
    }

    /**
     * 결과값은 이 함수로 들어온다. 정보를 취합하여 Msg로 콜백 핸들러에 전달한다.
     * @param o
     */
    @Override
    public void onResponse(Object o)
    {
//        XELLogUtil.d(XELGlobalDefine.TAG, "onResponse!!! TAG = " + csr.getTag());

        // 메세지 전달 번들
        Bundle bundle_callbackData = new Bundle();
        if (csr.getTag() != null)
        {
            bundle_callbackData.putString(TAG, csr.getTag().toString());
        }
        else
        {
            bundle_callbackData.putString(TAG, null);
        }
        bundle_callbackData.putString(DATA, (String) o);


        Message msg = new Message();
        msg.setData(bundle_callbackData);

        // 핸들러로 태그 및 데이터 전달
        callbackHandler.onDataResponseSucess(
                bundle_callbackData.getString(TAG),
                bundle_callbackData.getString(DATA));
    }
}
