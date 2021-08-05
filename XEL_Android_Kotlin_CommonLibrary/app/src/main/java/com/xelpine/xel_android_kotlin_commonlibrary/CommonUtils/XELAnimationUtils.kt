package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.View

/**
 * 애니메이션 셋 라이브러리.
 * 셋만을 만들어주는 것이므로 바로 실행되지 않는 것에 주의한다. 반드시 start 해야 시작된다.
 */
object XELAnimationUtils
{
    /**
     * 페이드인 애니메이션 셋
     * @param context
     * @param view
     * @param duration
     * @param listener
     * @return
     */
    fun AnimationSet_FadeIn
                (
        context: Context?,
        view: View?,
        duration: Long,
        listener: Animator.AnimatorListener?
    ): AnimatorSet
    {
        //sequencial animation (fade in)
        val objectAnimator_fadein = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val set_fadein = AnimatorSet()
        set_fadein.duration = duration
        set_fadein.play(objectAnimator_fadein)
        if (listener != null) {
            set_fadein.addListener(listener)
        }
        return set_fadein
    }

    /**
     * 페이드아웃 애니메이션 셋
     * @param context
     * @param view
     * @param duration
     * @param listener
     * @return
     */
    fun AnimationSet_FadeOut
                (
        context: Context?,
        view: View?,
        duration: Long,
        listener: Animator.AnimatorListener?
    ): AnimatorSet
    {
        //sequencial animation (fade in)
        val objectAnimator_fadeout = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val set_fadeout = AnimatorSet()
        set_fadeout.duration = duration
        set_fadeout.play(objectAnimator_fadeout)
        if (listener != null) {
            set_fadeout.addListener(listener)
        }
        return set_fadeout
    }

    /**
     * 직접 알파값 수정 애니메이션 셋
     * @param context
     * @param view
     * @param duration
     * @param current
     * @param after
     * @param listener
     * @return
     */
    fun AnimationSet_ChangAlpha(
        context: Context?,
        view: View?,
        duration: Long,
        current: Float,
        after: Float,
        listener: Animator.AnimatorListener?
    ): AnimatorSet
    {
        //sequencial animation (fade in)
        val objectAnimator_fadein = ObjectAnimator.ofFloat(view, "alpha", current, after)
        val set_fadein = AnimatorSet()
        set_fadein.duration = duration
        set_fadein.play(objectAnimator_fadein)
        if (listener != null) {
            set_fadein.addListener(listener)
        }
        return set_fadein
    }
}
