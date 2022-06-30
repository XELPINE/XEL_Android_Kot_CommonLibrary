package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 자산 실사 정보 Dto
 * 자산 정보를 기반으로 실사자, 실사 기간이 추가된다.
 */
class HiltClassBDto (
    var classBtitle : String
)
{
    @Inject constructor() : this("classBtitleclassBtitleclassBtitle")

    //lateinit var frag1Title : String // 실사자
}
