package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * charset을 사용할 수 있도록 수정한 VolleyStringRequest.
 * 인스턴스 생성 후 setCharset으로 charset 지정 후 queue에 add하면 된다.
 */
public class StringRequest_withCharset extends Request<String> {
    private final Listener<String> mListener;


    /**
     * the parse charset.
     */
    private String charset = null;
    Map<String, String> mParams = null;

    /**
     * Creates a new request with the given method.
     *
     * @param method the request {@link Method} to use
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public StringRequest_withCharset(int method, String url, Listener<String> listener,
                                     ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    /**
     * 바로 위의 생성자와 같지만 POST를 위해 map 정보를 넣는다.
     */
    public StringRequest_withCharset(int method, String url, HashMap<String, String> map, Listener<String> listener,
                                     ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;

        this.mParams = map;
    }



    /**
     * Creates a new GET request.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public StringRequest_withCharset(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    /**
     * Creates a new GET request with the given Charset.
     *
     * @param url URL to fetch the string at
     * @param listener Listener to receive the String response
     * @param errorListener Error listener, or null to ignore errors
     */
    public StringRequest_withCharset(String url, String charset, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
        this.charset = charset;
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            if(charset != null) {
                parsed = new String(response.data, charset);
            } else {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            }
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }



    /**
     * @return the Parse Charset Encoding
     */
    public String getCharset() {
        return charset;
    }

    /**
     * set the Parse Charset Encoding
     * @param charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

}