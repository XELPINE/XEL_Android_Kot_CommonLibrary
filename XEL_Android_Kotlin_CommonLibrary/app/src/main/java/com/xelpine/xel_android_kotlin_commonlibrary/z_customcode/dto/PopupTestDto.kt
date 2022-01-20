package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import java.io.Serializable

data class PopupTestDto (
    var popupCode : String,
    var popupName : String,
    var popupDesc : String?
        ) : XELCommonSelectionInterface, Serializable
{
    // 빈 생성자
//    constructor() : this("", "", null)

    override fun Code(): String {
        return popupCode
    }

    override fun Name(): String {
        return popupName
    }
}