package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil;

import java.util.HashMap;


public class XELVolleyUtil
{
    /**
     * Volley의 RequestQure로 정보를 던진다.
     * @param context : 컨텍스트
     * @param method : GET / POST
     * @param str_url : 정보를 수신할 URL
     * @param map_postData : POST 전송 시 전달할 데이터.
     * @param str_TAG : 정보를 구분하기 위한 TAG
     * @param str_Charset : 캐릭터 셋
     * @param policy : 네트워크 정책. (Timeout 시간 등)
     * @param responseHandler : 정보 수신 성공 시의 핸들러.
     * @param errorResponseHandler : 정보 수신 실패 시의 핸들러.
     */
    public static void startStringRequestData(Context context,
                                              int method,
                                              String str_url,
                                              HashMap<String, String> map_postData,
                                              String str_TAG,
                                              String str_Charset,
                                              DefaultRetryPolicy policy,
                                              XELVolleyResponseInterface responseInterface)
    {
        XELDialogUtil.LoadingDialog(context);

        // ResponseListener / ResponseErrorListener 인스턴스 생성
        Volley_Response_StringListener stringListener = new Volley_Response_StringListener();
        Volley_Response_StringErrorListener stringErrorListener = new Volley_Response_StringErrorListener();

        // stringRequest 인스턴스 생성
        StringRequest_withCharset stringRequest = null;
        if (method == Request.Method.GET)
            stringRequest = new StringRequest_withCharset(method, str_url, stringListener, stringErrorListener);
        else if (method == Request.Method.POST)
            stringRequest = new StringRequest_withCharset(method, str_url, map_postData, stringListener, stringErrorListener);

        // 태그 세팅
        stringRequest.setTag(str_TAG);

        // 캐릭터셋 세팅
        stringRequest.setCharset(str_Charset);

        // 그 외 정책 세팅 (TimeOut 시간 등)
        stringRequest.setRetryPolicy(policy);

        // 콜백 핸들러를 위한 작업
        stringListener.setHandler(responseInterface);
        stringListener.setCharsetStringRequest(stringRequest);
        stringErrorListener.setHandler(responseInterface);
        stringErrorListener.setCharsetStringRequest(stringRequest);

        // Queue에 정보 요청 시작. 값은 콜백 핸들러로 들어온다.
        XELGlobalApplication.getInstance().addToVolleyRequestQueue(stringRequest);
    }

    public interface XELVolleyResponseInterface
    {
        public void onDataResponseSucess(String tag, String data);

        public void onDataResponseError(String tag, int errorCode);

        public void onDataException(Exception e);

    }
}
