package com.xelpine.xel_android_kotlin_commonlibrary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELCommonShPreferences
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base

class MainActivity : XELActivity_Base() {

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun setPresetAnimation(): PresetAnimation {
        return PresetAnimation.NONE
    }

    override fun setWindowTransitions() {
    }

    override fun setNFCReadMode(): NFCReadMode {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
    }

    override fun initData() {
    }

    override fun DisplayLandscapeAfter() {
    }

    override fun DisplayPortraitAfter() {
    }

    override fun initAfterLogic() {

        val intent_start : Intent = Intent(this, Activity_MainMenu::class.java)
        startActivity(intent_start)


    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}