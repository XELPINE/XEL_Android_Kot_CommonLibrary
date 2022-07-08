package com.xelpine.xel_android_kotlin_commonlibrary

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELNFCUtil
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LibraryUnitTest
{
    @Test
    fun testNFC() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext


        Assert.assertEquals(true, XELNFCUtil.checkNfcEnabled(appContext))
    }
}