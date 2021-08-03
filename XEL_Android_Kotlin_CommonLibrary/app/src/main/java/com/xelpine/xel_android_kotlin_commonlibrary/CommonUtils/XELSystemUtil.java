package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.sergivonavi.materialbanner.Banner;
import com.sergivonavi.materialbanner.BannerInterface;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;
import com.xelpine.xel_android_kotlin_commonlibrary.R;


public class XELSystemUtil {

    /**
     * OS 버전
     * @return
     */
    public static int getOsVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 앱 버전
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String version = "";
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * 다크모드 활성화 확인.
     * true : 다크모드
     * false : 라이트모드
     * @param context
     * @return
     */
    public static boolean getDarkMode (Context context)
    {
        int currentNightMode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active on device

                XELLogUtil.d_function(XELGlobalDefine.TAG, "getDarkMode (다크모드 활성화 확인) : false");
                return false;

            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active on device

                XELLogUtil.d_function(XELGlobalDefine.TAG, "getDarkMode (다크모드 활성화 확인) : true");
                return true;
        }

        return false;
    }

    /**
     * 네비게이션 바 높이를 구한다.
     * @param context
     * @return
     */
    public static int getNavigationBarHeight (Context context)
    {
        int navigationBarHeight = 0;
        int resId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resId > 0) {
            navigationBarHeight = context.getResources().getDimensionPixelSize(resId);
        }

        return navigationBarHeight;
    }

    /**
     * 스테이터스바 높이 구하기. addMargin으로 추가하고 싶은 값을 추가할 수도 있다.
     * @param context
     * @param addMargin
     * @return
     */
    public static int getStatusBarHeight (Context context, int addMargin)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        XELLogUtil.d_function(XELGlobalDefine.TAG, "getStatusBarHeight (스테이터스바 높이) size : " + result);

        return result+addMargin;
    }

    /**
     * 기기가 노치를 가지고 있는지 유무를 판단한다.
     * 안드로이드 9.0 이상에서만 동작한다.
     * 9.0 미만이면 무조건 노치가 없다고 리턴한다.
     * @param activity
     * @return
     */
    public static boolean isDeviceNotchExist (Activity activity)
    {
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

        boolean isNotch = false;

        if (SDK_INT >= Build.VERSION_CODES.P)
        {
            DisplayCutout displayCutout = activity.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();

            if (displayCutout == null)
            {
                isNotch = false;
            }
            else
            {
                isNotch = true;
            }
        }
        else
        {
            isNotch = false;
        }

        XELLogUtil.d_function(XELGlobalDefine.TAG, "isDeviceNotchExist (기기의 노치 유무) : " + isNotch);

        return isNotch;

    }

    public static boolean isActionBarBackButtonEnabled (AppCompatActivity appCompatActivity)
    {
        boolean isBackButtonEnabled = false;

        if ((appCompatActivity.getSupportActionBar().getDisplayOptions() & ActionBar.DISPLAY_HOME_AS_UP) != 0)
        {
            isBackButtonEnabled = true;
        }
        else
        {
            isBackButtonEnabled = false;
        }

        XELLogUtil.d_function(XELGlobalDefine.TAG, "isActionBarBackButtonEnabled (액션바에 백버튼 유무) : " + isBackButtonEnabled);

        return isBackButtonEnabled;
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
    public static void hideKeyboard (Context context, EditText editText)
    {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 스낵바 보이기
     * @param context
     * @param coordinatorLayout
     * @param title
     */
    public static void ShowSnackbar (Context context, CoordinatorLayout coordinatorLayout, String title)
    {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, title, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static void ShowSnackbar (Context context, CoordinatorLayout coordinatorLayout, String title, @ColorInt int color)
    {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, title, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(color);
        snackbar.show();
    }

    /**
     * 배너 보이기 (버튼 2개)
     * @param banner
     * @param message
     * @param onClickListener_left
     * @param onClickListener_right
     */
    public static void ShowBanner_TwoButtons (Context context, @DrawableRes int iconId, Banner banner, String message, String leftButtonTitle, String rightButtonTitle, BannerInterface.OnClickListener onClickListener_left, BannerInterface.OnClickListener onClickListener_right)
    {
        banner.setIcon(iconId);
        banner.setIconTintColor(R.color.common_black_nightmode);
//        banner.setBackgroundColor(context.getResources().getColor(R.color.duzonColor_50pertransparent));
        banner.setMessage(message);
        banner.setLeftButton(leftButtonTitle, onClickListener_left);
        banner.setRightButton(rightButtonTitle, onClickListener_right);

        // show when needed
        banner.show();
    }

    /**
     * 배너 보이기 (버튼 1개)
     * @param banner
     * @param message
     * @param onClickListener_right
     */
    public static void ShowBanner_OneButton (Banner banner, String message, String rightButtonTitle, BannerInterface.OnClickListener onClickListener_right)
    {
        banner.setMessage(message);
        banner.setRightButton(rightButtonTitle, onClickListener_right);

        // show when needed
        banner.show();
    }

    /**
     * 스테이터스바 색상 적용
     * @param activity
     * @param color
     */
    public static void setStatusBarColor (Activity activity, @ColorInt int color)
    {
        activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
    }
}
