package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils;

import android.os.Bundle;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/**
 * Volley의 StringRequest 중 ErrorListner의 확장 클래스. (에러발생 시 불리는 리스너)
 *
 * 인스턴스 생성 후
 * setHandler로 콜백 핸들러 설정,
 * setCharsetStringRequest로 StringRequsetwithCharset을 지정해야 한다.
 *
 * handler로 결과값이 전달되며 에러코드는 아래에 있다.
 */
public class Volley_Response_StringErrorListener implements Response.ErrorListener
{
    XELVolleyUtil.XELVolleyResponseInterface callbackHandler; // 콜백 핸들러
    StringRequest_withCharset csr;

    // 결과값 Key
    public final static String TAG = "tag";                 // 결과값의 TAG value
    public final static String ERROR_CODE = "error_code";   // 결과값의 ERROR_CODE value

    // 에러코드 종류
    public static final int ERRORCODE_TIMEOUT = 0;          // 타임아웃
    public static final int ERRORCODE_NO_CONNECTION = 1;    // 연결상태가 아님
    public static final int ERRORCODE_AUTH_FAILURE = 2;     // 인증 실패
    public static final int ERRORCODE_SERVER = 3;           // 서버 에러 (일부 사이트의 경우 URL이 틀리면 이쪽으로 날아온다.)
    public static final int ERRORCODE_NETWORK = 4;          // 네트워크 에러
    public static final int ERRORCODE_PARSE = 5;            // 파싱 에러

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
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error)
    {
//        XELLogUtil.d(XELGlobalDefine.TAG, "onErrorResponse!!! TAG = " + csr.getTag() + " / " + error.getMessage());

        // 메세지 전달 번들
        Bundle bundle_callbackData = new Bundle();

        // TAG 세팅
        if (csr.getTag() != null)
        {
            bundle_callbackData.putString(TAG, csr.getTag().toString());
        }
        else
        {
            bundle_callbackData.putString(TAG, null);
        }

        // ERROR_CODE 세팅
        // 타임아웃 에러
        if (error instanceof TimeoutError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_TIMEOUT);
        }
        // 네트워크 연결이 모두 끊어진 경우
        else if (error instanceof NoConnectionError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_NO_CONNECTION);
        }
        // 인증 실패
        else if (error instanceof AuthFailureError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_AUTH_FAILURE);
        }
        // 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.
        else if (error instanceof ServerError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_SERVER);
        }
        // 네트워크 에러
        else if (error instanceof NetworkError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_NETWORK);
        }
        // 파싱 에러
        else if (error instanceof ParseError)
        {
            bundle_callbackData.putInt(ERROR_CODE, ERRORCODE_PARSE);
        }

        Message msg = new Message();
        msg.setData(bundle_callbackData);

        // 콜백 핸들러로 에러 코드 전달
        callbackHandler.onDataResponseError(
                bundle_callbackData.getString(TAG),
                bundle_callbackData.getInt(Volley_Response_StringErrorListener.ERROR_CODE));
    }
}
