package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.os.Handler
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine

/**
 * 핸들러 관련 유틸리티. postDelayed 위주
 * 마지막 업데이트 : 2021-07-15
 */
object XELHandlerUtil {
    var handler = Handler()

    /**
     * 딜레이 후 콜백
     * @param delayed
     * @param delayedCompleteCallback
     */
    fun PostDelayed(delayed: Long, delayedCompleteCallback: DelayedCompleteCallback) {

        XELLogUtil.i_function(XELGlobalDefine.TAG, "PostDelayed (핸들러Util) 시작")

        handler.removeMessages(0)
        handler.postDelayed(
            {
                XELLogUtil.i_function(XELGlobalDefine.TAG, "PostDelayed (핸들러Util) DelayComplete")
                delayedCompleteCallback.DelayComplete()
            },
            delayed
        )
    }

    // 리턴 인터페이스
    interface DelayedCompleteCallback {
        fun DelayComplete()
    }
}
