package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION
import android.view.DisplayCutout
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.sergivonavi.materialbanner.Banner
import com.sergivonavi.materialbanner.BannerInterface
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.R
import android.view.KeyCharacterMap
import android.view.KeyEvent


object XELSystemUtil {
    /**
     * OS 버전
     * @return
     */
    val osVersion: Int
        get() = VERSION.SDK_INT

    /**
     * 앱 버전
     * @param context
     * @return
     */
    fun getAppVersion(context: Context): String {
        var version = ""
        try {
            version = context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return version
    }

    /**
     * 다크모드 활성화 확인.
     * true : 다크모드
     * false : 라이트모드
     * @param context
     * @return
     */
    fun getDarkMode(context: Context): Boolean {
        val currentNightMode =
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                // Night mode is not active on device
                XELLogUtil.d_function(XELGlobalDefine.TAG, "getDarkMode (다크모드 활성화 확인) : false")
                return false
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                // Night mode is active on device
                XELLogUtil.d_function(XELGlobalDefine.TAG, "getDarkMode (다크모드 활성화 확인) : true")
                return true
            }
        }
        return false
    }

    /**
     * 내비게이션 바가 현재 화면에 보이는 지를 체크.
     * 정확하진 않다..
     */
    fun hasNavBar(resources: Resources): Boolean {
//        val id: Int = resources.getIdentifier("config_showNavigationBar", "bool", "android")
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "hasNavBar (내비게이션 바 존재유무) : " + (id > 0 && resources.getBoolean(id)))
//        return id > 0 && resources.getBoolean(id)

        val useSoftNavigation: Boolean
        val id: Int = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        useSoftNavigation = if (id > 0) {
            resources.getBoolean(id)
        } else {
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            val hasHomeKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_HOME)

            !(hasBackKey && hasHomeKey)
        }

        XELLogUtil.d_function(XELGlobalDefine.TAG, "hasNavBar (내비게이션 바 존재유무) : " + useSoftNavigation)

        return useSoftNavigation
    }

    /**
     * 네비게이션 바 높이를 구한다.
     * @param context
     * @return
     */
    fun getNavigationBarHeight(context: Context): Int {
        var navigationBarHeight = 0
        val resId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resId > 0) {
            navigationBarHeight = context.resources.getDimensionPixelSize(resId)
        }
        return navigationBarHeight
    }

    /**
     * 스테이터스바 높이 구하기. addMargin으로 추가하고 싶은 값을 추가할 수도 있다.
     * @param context
     * @param addMargin
     * @return
     */
    fun getStatusBarHeight(context: Context, addMargin: Int): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        XELLogUtil.d_function(XELGlobalDefine.TAG, "getStatusBarHeight (스테이터스바 높이) size : $result")
        return result + addMargin
    }

    /**
     * 기기가 노치를 가지고 있는지 유무를 판단한다.
     * 안드로이드 9.0 이상에서만 동작한다.
     * 9.0 미만이면 무조건 노치가 없다고 리턴한다.
     * @param activity
     * @return
     */
    fun isDeviceNotchExist(activity: Activity): Boolean {
//        boolean isNotch = false;
//
//        if (getStatusBarHeight(context, 0) > XELDisplayUtil.dpToPixel(24))
//        {
//            isNotch = true;
//        }
//        else
//        {
//            isNotch = false;
//        }
//
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "isDeviceNotchExist (기기의 노치 유무) : " + isNotch);
//
//        return isNotch;
        var isNotch = false
        isNotch = if (VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val displayCutout = activity.window.decorView.rootWindowInsets.displayCutout
            if (displayCutout == null) {
                false
            } else {
                true
            }
        } else {
            false
        }
        XELLogUtil.d_function(XELGlobalDefine.TAG, "isDeviceNotchExist (기기의 노치 유무) : $isNotch")
        return isNotch
    }

    fun isActionBarBackButtonEnabled(appCompatActivity: AppCompatActivity): Boolean {
        var isBackButtonEnabled = false
        isBackButtonEnabled =
            if (appCompatActivity.supportActionBar!!.displayOptions and ActionBar.DISPLAY_HOME_AS_UP != 0) {
                true
            } else {
                false
            }
        XELLogUtil.d_function(
            XELGlobalDefine.TAG,
            "isActionBarBackButtonEnabled (액션바에 백버튼 유무) : $isBackButtonEnabled"
        )
        return isBackButtonEnabled
    }
    //    /**
    //     * 단말기 고유식별번호 얻어오기
    //     *
    //     * @return
    //     */
    //    public static String getDeviceId(Context ctxt) throws Exception {
    //        String szDeviceId = null;
    //
    //        if (isWifiModel(ctxt)) {
    //            WifiManager wifiMan = (WifiManager) ctxt.getSystemService(Context.WIFI_SERVICE);
    //            WifiInfo wifiInfo = wifiMan.getConnectionInfo();
    //
    //            szDeviceId = (wifiInfo == null) ? null : wifiInfo.getBSSID() + wifiInfo.getNetworkId() + wifiInfo.getSSID();
    //        } else {
    ////            TelephonyManager tman = (TelephonyManager) ctxt.getSystemService(Context.TELEPHONY_SERVICE);
    ////            szDeviceId = tman.getDeviceId();
    //
    //            szDeviceId = getUniqueDeviceID(ctxt);
    //        }
    //
    //        return szDeviceId;
    //    }
    //    /**
    //     * WIFI 전용 단말기 여부 확인하기 ctxt Activity Context
    //     *
    //     * @return
    //     */
    //    public static boolean isWifiModel(Context ctxt) {
    //        boolean bRtn = false;
    //        String szDeviceId = null;
    //
    //        TelephonyManager tman = (TelephonyManager) ctxt.getSystemService(Context.TELEPHONY_SERVICE);
    //
    //        szDeviceId = tman.getDeviceId();
    //
    //        if (szDeviceId == null || szDeviceId.length() == 0) {
    //            bRtn = true;
    //        }
    //
    //        return bRtn;
    //    }
    //    /**
    //     * 유니크한 DeviceID를 생성해서 반환한다..
    //     * <p/>
    //     * 중요) <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    //     * 퍼미션를 추가해야한다..
    //     *
    //     * @param context
    //     * @return
    //     */
    //    public static String getUniqueDeviceID(Context context) {
    //        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    //
    //        String tmDevice = "" + tm.getDeviceId();
    //        String tmSerial = "" + tm.getSimSerialNumber();
    //        String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    //
    //        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
    //
    //        return deviceUuid.toString();
    //    }
    /**
     * 키보드 감추기
     * @param context
     * @param editText
     */
    fun hideKeyboard(context: Context, editText: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    /**
     * 스낵바 보이기
     * @param context
     * @param coordinatorLayout
     * @param title
     */
    fun ShowSnackbar(context: Context?, coordinatorLayout: CoordinatorLayout?, title: String?) {
        val snackbar = Snackbar.make(coordinatorLayout!!, title!!, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun ShowSnackbar(
        context: Context?,
        coordinatorLayout: CoordinatorLayout?,
        title: String?,
        @ColorInt color: Int
    ) {
        val snackbar = Snackbar.make(coordinatorLayout!!, title!!, Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(color)
        snackbar.show()
    }

    /**
     * 배너 보이기 (버튼 2개)
     * @param banner
     * @param message
     * @param onClickListener_left
     * @param onClickListener_right
     */
    fun ShowBanner_TwoButtons(
        context: Context?,
        @DrawableRes iconId: Int,
        banner: Banner,
        message: String?,
        leftButtonTitle: String?,
        rightButtonTitle: String?,
        onClickListener_left: BannerInterface.OnClickListener?,
        onClickListener_right: BannerInterface.OnClickListener?
    ) {
        banner.setIcon(iconId)
        banner.setIconTintColor(R.color.mainColor)
        //        banner.setBackgroundColor(context.getResources().getColor(R.color.duzonColor_50pertransparent));
        banner.setMessage(message)
        banner.setLeftButton(leftButtonTitle, onClickListener_left)
        banner.setRightButton(rightButtonTitle, onClickListener_right)

        // show when needed
        banner.show()
    }

    /**
     * 배너 보이기 (버튼 1개)
     * @param banner
     * @param message
     * @param onClickListener_right
     */
    fun ShowBanner_OneButton(
        banner: Banner,
        message: String?,
        rightButtonTitle: String?,
        onClickListener_right: BannerInterface.OnClickListener?
    ) {
        banner.setMessage(message)
        banner.setRightButton(rightButtonTitle, onClickListener_right)

        // show when needed
        banner.show()
    }

    /**
     * 스테이터스바 색상 적용
     * @param activity
     * @param color
     */
    fun setStatusBarColor(activity: Activity, @ColorInt color: Int) {
        activity.window.statusBarColor = activity.resources.getColor(color)
    }
}
