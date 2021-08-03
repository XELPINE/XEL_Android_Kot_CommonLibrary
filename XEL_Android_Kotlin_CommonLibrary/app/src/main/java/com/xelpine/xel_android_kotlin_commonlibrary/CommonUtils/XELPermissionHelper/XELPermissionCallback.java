package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELPermissionHelper;

/**
 * 2021-07-08 : 전체 권한 허용 관련 인터페이스 추가
 */
public interface XELPermissionCallback
{
    // 모든 권한 허용됨
    void PermissionPermitedAll (String[] str_permissionName);

    // 퍼미션 일부 허용
    void PermissionPermitedPart (String[] str_permissionName);

    // 퍼미션 일부 거부됨 or 다시 보지 않기 선택됨
    void PermissionDenied (String[] str_permissionName);

    // 퍼미션 요청 다이얼로그 거부
    void PermitDialogDenial ();
}
