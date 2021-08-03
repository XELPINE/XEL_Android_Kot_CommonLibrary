package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

/**
 * 애니메이션 셋 라이브러리.
 * 셋만을 만들어주는 것이므로 바로 실행되지 않는 것에 주의한다. 반드시 start 해야 시작된다.
 */
public class XELAnimationUtils {

    /**
     * 페이드인 애니메이션 셋
     * @param context
     * @param view
     * @param duration
     * @param listener
     * @return
     */
    public static AnimatorSet AnimationSet_FadeIn (Context context, View view, long duration, Animator.AnimatorListener listener)
    {
        //sequencial animation (fade in)
        ObjectAnimator objectAnimator_fadein = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);

        AnimatorSet set_fadein = new AnimatorSet();
        set_fadein.setDuration(duration);
        set_fadein.play(objectAnimator_fadein);
        if (listener != null)
        {
            set_fadein.addListener(listener);
        }

        return set_fadein;
    }

    /**
     * 페이드아웃 애니메이션 셋
     * @param context
     * @param view
     * @param duration
     * @param listener
     * @return
     */
    public static AnimatorSet AnimationSet_FadeOut (Context context, View view, long duration, Animator.AnimatorListener listener)
    {
        //sequencial animation (fade in)
        ObjectAnimator objectAnimator_fadeout = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);

        AnimatorSet set_fadeout = new AnimatorSet();
        set_fadeout.setDuration(duration);
        set_fadeout.play(objectAnimator_fadeout);
        if (listener != null)
        {
            set_fadeout.addListener(listener);
        }

        return set_fadeout;
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
    public static AnimatorSet AnimationSet_ChangAlpha (Context context, View view, long duration, float current, float after, Animator.AnimatorListener listener)
    {
        //sequencial animation (fade in)
        ObjectAnimator objectAnimator_fadein = ObjectAnimator.ofFloat(view, "alpha", current, after);

        AnimatorSet set_fadein = new AnimatorSet();
        set_fadein.setDuration(duration);
        set_fadein.play(objectAnimator_fadein);
        if (listener != null)
        {
            set_fadein.addListener(listener);
        }

        return set_fadein;
    }

}
