package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.os.Handler;

/**
 * 핸들러 관련 유틸리티. postDelayed 위주
 * 마지막 업데이트 : 2021-07-15
 */
public class XELHandlerUtil {

    static Handler handler = new Handler();

    // 리턴 인터페이스
    public interface DelayedCompleteCallback
    {
        void DelayComplete();
    }

    /**
     * 딜레이 후 콜백
     * @param delayed
     * @param delayedCompleteCallback
     */
    public static void PostDelayed (long delayed, DelayedCompleteCallback delayedCompleteCallback)
    {
        handler.removeMessages(0);

        handler.postDelayed(new Runnable()
                            {
                                @Override
                                public void run() {

                                    delayedCompleteCallback.DelayComplete();

                                }
                            },
                delayed);
    }

}
