package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.content.Context;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;

public class XELNFCUtil {

    /**
     * NFC가 켜져있는지 확인
     * @return
     */
    public static boolean checkNfcEnabled(Context context)
    {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "checkNfcEnabled (NFC 활성화 체크)");

        Boolean nfcEnabled = XELGlobalApplication.getInstance().getNfcAdapter(context).isEnabled();

        XELLogUtil.d_function(XELGlobalDefine.TAG, "checkNfcEnabled (NFC 활성화 체크 결과) : " + nfcEnabled);

        // NFC 꺼져있음
        if (nfcEnabled == false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
