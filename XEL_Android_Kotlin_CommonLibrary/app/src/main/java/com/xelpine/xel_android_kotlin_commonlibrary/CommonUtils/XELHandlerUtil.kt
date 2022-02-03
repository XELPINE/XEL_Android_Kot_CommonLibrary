package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.os.Handler

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
        handler.removeMessages(0)
        handler.postDelayed(
            {
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
