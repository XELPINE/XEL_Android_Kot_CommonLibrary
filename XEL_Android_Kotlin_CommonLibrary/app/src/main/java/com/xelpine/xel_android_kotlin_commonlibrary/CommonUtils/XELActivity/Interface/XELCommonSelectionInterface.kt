package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface

// 일반 코드 선택
interface XELCommonSelectionInterface
{
    // 코드
    fun Code(): String

    // 이름
    fun Name(): String

    // 선택시 이름+화살표 추가 표기
    fun NameWithSelector(): String
}
