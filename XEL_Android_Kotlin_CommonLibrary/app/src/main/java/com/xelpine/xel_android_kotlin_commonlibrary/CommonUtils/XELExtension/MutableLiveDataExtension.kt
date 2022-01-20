package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELExtension

import androidx.lifecycle.MutableLiveData

/**
 * MutableLiveData 확장코드
 */

// 값을 다시 덮어써서 ArrayList에서 내부 데이터가 변경될 경우 옵저버를 강제호출하도록 한다.
fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}