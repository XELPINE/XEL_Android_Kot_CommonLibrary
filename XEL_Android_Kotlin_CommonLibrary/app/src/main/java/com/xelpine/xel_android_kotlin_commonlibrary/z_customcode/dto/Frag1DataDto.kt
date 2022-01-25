package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import java.io.Serializable

/**
 * 자산 실사 정보 Dto
 * 자산 정보를 기반으로 실사자, 실사 기간이 추가된다.
 */
data class Frag1DataDto (
    var frag1Title : String, // 실사자
    var frag1Desc : String // 실사시간
        ): Serializable, XELCommonSelectionInterface
{
    // 추가 생성자
    constructor() : this("", "")


    override fun Code(): String {
        return frag1Title
    }

    override fun Name(): String {
        return frag1Desc
    }

    override fun NameWithSelector(): String {
        return frag1Title + " ▼"
    }
}
