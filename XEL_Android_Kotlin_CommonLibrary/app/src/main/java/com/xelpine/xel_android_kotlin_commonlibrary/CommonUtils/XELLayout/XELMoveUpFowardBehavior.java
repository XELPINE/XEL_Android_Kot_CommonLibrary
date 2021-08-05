package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.snackbar.Snackbar;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil;

/**
 * 코디네이터 레이아웃의 BeHavior를 커스텀 정의한다.
 * 바닥에서 스낵바가 튀어오르는 것에 대해 레이아웃을 끌어올렸다가 내리는 작업을 담당한다.
 * 사용 시 레이아웃에서 app:layout_behavior에 이 클래스를 추가한다.
 */
public class XELMoveUpFowardBehavior extends CoordinatorLayout.Behavior<View> {
    public XELMoveUpFowardBehavior() {
        super();
    }

    public XELMoveUpFowardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    // Behavior로 뷰가 바뀌기 시작할 때
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        XELLogUtil.Companion.d_function(XELGlobalDefine.TAG, "onDependentViewChanged");

        // 스낵바 높이만큼만 올라가기 때문에 15dip 정도 값을 더 줘서 오르도록 한다.
        float translationY = Math.min(0, ViewCompat.getTranslationY(dependency)-15 - dependency.getHeight());

        // 이렇게 하면 화면이 바로 전환된다.
//        ViewCompat.setTranslationY(child, translationY);

        // 이건 애니메이션을 줘서 움직이도록 한다.
        ViewCompat.animate(child).translationY(translationY).start();
        return true;
    }

    // Behavior로 뷰가 삭제될 때
    //you need this when you swipe the snackbar(thanx to ubuntudroid's comment)
    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        XELLogUtil.Companion.d_function(XELGlobalDefine.TAG, "onDependentViewRemoved");

        ViewCompat.animate(child).translationY(0).start();
    }
}
