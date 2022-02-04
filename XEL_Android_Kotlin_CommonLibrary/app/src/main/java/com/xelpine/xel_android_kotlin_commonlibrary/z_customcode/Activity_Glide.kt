package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.bumptech.glide.Glide
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.R

class Activity_Glide : XELActivity_Base() {

    var toolbar: Toolbar? = null
    private var imageView1: ImageView? = null
    private var imageView2: ImageView? = null
    private var imageView3: ImageView? = null
    private var imageView4: ImageView? = null

    protected override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
    }

    protected override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.NONE
    }

    protected override fun setWindowTransitions() {
        val slide = Slide()
        slide.duration = 300
        slide.slideEdge = Gravity.BOTTOM
        slide.interpolator = FastOutSlowInInterpolator()
        slide.setMatchOrder()
        getWindow().setEnterTransition(slide)
    }

    protected override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    protected override fun initLayout() {
        imageView1 = findViewById(R.id.imageView1) as ImageView?
        imageView2 = findViewById(R.id.imageView2) as ImageView?
        imageView3 = findViewById(R.id.imageView3) as ImageView?
        imageView4 = findViewById(R.id.imageView4) as ImageView?
        toolbar = findViewById(R.id.toolbar) as Toolbar?
        /**
         * setListener
         */
        setSupportActionBar(toolbar)
        toolbar!!.title = "GLIDE"

        // 뒤로가기 버튼
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    protected override fun initData() {
        Glide.with(this)
            .load("https://cdn.pixabay.com/photo/2013/07/12/17/47/test-pattern-152459_960_720.png")
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView1!!)
        Glide.with(this)
            .load("https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/befbcde0-9b36-11e6-95b9-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg")
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView2!!)
        Glide.with(this)
            .load("https://assets.fireside.fm/file/fireside-images/podcasts/images/b/bc7f1faf-8aad-4135-bb12-83a8af679756/cover.jpg?v=1")
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView3!!)
        Glide.with(this)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQzERqpiBq1uTAsaX78EwVtjGUuae-XMENXiw&usqp=CAU")
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView4!!)
    }

    protected override fun displayLandscapeAfter() {}

    protected override fun displayPortraitAfter() {}

    protected override fun initAfterLogic() {}

    protected override fun doPause() {}

    protected override fun doResume() {}

    override fun doStart() {
    }

    override fun doStop() {
    }

    protected override fun doDestroy() {}

}