package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentFilter.MalformedMimeTypeException
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELSystemUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils.XELVolleyUtil.XELVolleyResponseInterface
import com.xelpine.xel_android_kotlin_commonlibrary.MainActivity
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.lang.Exception
import java.lang.RuntimeException

/**
 * 베이스 액티비티.
 *
 * 필수 : toolbar를 사용한다면 반드시 id가 toolbar여야 한다.
 */
abstract class XELActivity_Base : AppCompatActivity(), XELVolleyResponseInterface
{
    // NFC Read
    private var mNfcPendingIntent: PendingIntent? = null
    lateinit var mReadTagFilters: Array<IntentFilter>

    /**
     * NFC 읽기 모드 선택
     */
    enum class NFCReadMode {
        NONE, READ
    }

    // 선택된 NFC 읽기 모드
    private var selectedNFCReadMode: NFCReadMode? = NFCReadMode.NONE

    /**
     * 애니메이션과 Transition (setWindowAnimations) 의 경우, 트랜지션이 우선권을 가진다.
     * 즉, 트랜지션을 구현 (setWindowAnimations에 구현 및 startActivity에 소스 추가) 시 애니메이션은 작동하지 않는다.
     */
    enum class PresetAnimation {
        NONE, SLIDE_RIGHT, SLIDE_BOTTOM, FADE
    }

    // 선택된 애니메이션
    private var selectedPresetAnimation: PresetAnimation? = PresetAnimation.NONE

    /**
     * 베이스 액티비티의 애니메이션 기본값 세팅
     */
    private fun transAnimationSetting()
    {
        // 킷캣 이상일 때, 스테이터스바 (최상단 시스템 바) 도 완전히 투명하게 보이도록 한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            val w = window
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            w.setFlags(
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
            )
            w.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            w.statusBarColor = Color.TRANSPARENT
        }

        when (selectedPresetAnimation)
        {
            PresetAnimation.NONE ->
            {
            }

            PresetAnimation.SLIDE_RIGHT ->
                overridePendingTransition(
                    R.anim.xel_anim_slide_right_in,
                    R.anim.xel_anim_none
                )

            PresetAnimation.SLIDE_BOTTOM ->
                overridePendingTransition(
                    R.anim.xel_anim_slide_bottom_in,
                    R.anim.xel_anim_none
                )

            PresetAnimation.FADE ->
                overridePendingTransition(
                    R.anim.xel_anim_fade_in,
                    R.anim.xel_anim_none
                )
        }
    }

    /**
     * onCreate() 최상단에서 처리가 필요한 경우 사용. (ex : 윈도우 속성 변경 등..)
     */
    protected open fun beforeCreate() {

        // 라이트모드에서는 라이트 네비게이션 바를 사용하도록
        if (XELSystemUtil.getDarkMode(this) == false) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

//        getWindow().setStatusBarColor(getResources().getColor(R.color.duzonColor));

        // 네비바 투명
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    /**
     * Activity 초기화 루틴을 실제로 구현하는 부분.
     *
     * @param savedInstanceState
     */
    protected open fun doCreate(savedInstanceState: Bundle?) {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "doCreate")
    }

    /**
     * 프리셋 애니메이션 세팅. 애니메이션을 바꾸고 싶다면 해당 값을 오버라이드한다.
     */
    protected abstract fun setPresetAnimation(): PresetAnimation?

    /**
     * 트랜지션 세팅
     * EnterTransition <--> ReturnTransition
     * ExitTransition <--> ReenterTransition
     */
    protected abstract fun setWindowTransitions()

    /**
     * NFC 읽기 모드 선택
     * @return
     */
    protected abstract fun setNFCReadMode(): NFCReadMode?

    /**
     * 레이아웃 세팅. findViewById, setListener 등
     */
    protected abstract fun initLayout()

    /**
     * 뷰가 윈도우에 연결될 때 호출된다.
     * 그리기 시작하기 가능한 경우부터 이 메소드가 불린다.
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val r = Resources.getSystem()
        val config = r.configuration
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttachedToWindow ORIENTATION_LANDSCAPE")

            displayLandscapeAfter()

            // 가로모드
            try {
                if (XELSystemUtil.isDeviceNotchExist(this)) {
                    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
                    toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 50), 0, 0, 0)
                    if (XELSystemUtil.isActionBarBackButtonEnabled(this) == false) {
                        toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 100), 0, 0, 0)
                    }
                }
            } catch (e: Exception) {
                XELLogUtil.e(XELGlobalDefine.TAG, "", e)
            }
        } else {
            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttachedToWindow ORIENTATION_PORTRAIT")

            displayPortraitAfter()

            // 세로모드
            try {
                if (XELSystemUtil.isDeviceNotchExist(this)) {
                    val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
                    toolbar.setPadding(0, 0, 0, 0)
                }
            } catch (e: Exception) {
                XELLogUtil.e(XELGlobalDefine.TAG, "", e)
            }
        }
    }

    /**
     * 기본값 세팅
     */
    protected abstract fun initData()

    /**
     * 화면이 가로로 변했을 때 (진입 시, 진입 후 화면 전환 시 모두 다)
     */
    protected abstract fun displayLandscapeAfter()

    /**
     * 화면이 세로로 변했을 때 할 작업 (진입 시, 진입 후 화면 전환 시 모두 다)
     */
    protected abstract fun displayPortraitAfter()

    /**
     * onCreate의 모든 초기화 종료 후 추가 로직
     */
    protected abstract fun initAfterLogic()

    /**
     * Activity가 pause 상태에 진입을 하고, 실제로 activity 가 pause call back을 처리해야하는 상황일때 호출한다.
     */
    protected abstract fun doPause()

    protected abstract fun doResume()

    protected abstract fun doDestroy()

    protected fun doStart() {}

    protected fun doStop() {}

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
        {
            XELLogUtil.e(XELGlobalDefine.TAG, "onCreate() is NOT Null")
            XELLogUtil.e(XELGlobalDefine.TAG, "App이 비정상으로 종료되어 App을 재시작합니다.")
            ActivityCompat.finishAffinity(this@XELActivity_Base)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            return
        }
        else
        {

            // 1. 프리셋 애니메이션 종류 선택
            selectedPresetAnimation = setPresetAnimation()
            if (selectedPresetAnimation == null) selectedPresetAnimation = PresetAnimation.NONE

            // 2. 외부의 트랜지션 세팅. 해당 코드에서 트랜지션을 세팅하면 위의 애니메이션은 무시된다.
            setWindowTransitions()

            // 기본 애니메이션 세팅. 오버라이드 불가.
            transAnimationSetting()

            // 3. 윈도우 속성 변경 등
            beforeCreate()

            // 4. Activity Create 실행
            doCreate(savedInstanceState)

            /**
             * NFC 로직 추가
             */
            selectedNFCReadMode = setNFCReadMode()

            if (selectedNFCReadMode == null)
                selectedNFCReadMode = NFCReadMode.NONE

            if (selectedNFCReadMode == NFCReadMode.READ)
            {
                NFCReadInit()
            }


            // 5
            initLayout()

            // 6
            initData()

            // 7
            initAfterLogic()
        }

//        LogUtil.d(TAG, "Life onCreate() " + this.getClass().getSimpleName());
    }

    /**
     * NFC 읽기모드의 초기화
     */
    private fun NFCReadInit() {
//        mNfcAdapter = XELGlobalApplication.getInstance().getNfcAdapter(this);
        XELGlobalApplication.getInstance().getNfcAdapter(this)

        // Handle foreground NFC scanning in this activity by creating a
        // PendingIntent with FLAG_ACTIVITY_SINGLE_TOP flag so each new scan
        // is not added to the Back Stack
        mNfcPendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            0
        )

        // Create intent filter to handle NDEF NFC tags detected from inside our
        // application when in "read mode":
        val ndefDetected = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        try {
            ndefDetected.addDataType("application/com.fernandocejas.example.android.nfc")
        } catch (e: MalformedMimeTypeException) {
            throw RuntimeException("Could not add MIME type.", e)
        }
        mReadTagFilters = arrayOf(ndefDetected)
    }

    override fun onStart() {
        super.onStart()
        //        LogUtil.d(TAG, "Life onStart() " + this.getClass().getSimpleName());
        doStart()
    }

    override fun onResume() {
        super.onResume()

//        LogUtil.d(TAG, "Life onResume() " + this.getClass().getSimpleName());
        XELLogUtil.d_function(XELGlobalDefine.TAG, "onResume")


        // NFC Read
        if (selectedNFCReadMode == NFCReadMode.READ) {
            if (intent.action != null) {
                // tag received when app is not running and not in the foreground:
                if (intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
                    val msgs = getNdefMessagesFromIntent(intent)
                    val record = msgs!![0]!!.records[0]
                    val payload = record.payload
                    val payloadString = String(payload)
                    if (payloadString == "vader") {
                        finish()
                        //                	startActivity(new Intent(ReadTagActivity.this, VaderActivity.class));
                    } else if (payloadString == "ass") {
                        finish()
                        //                	startActivity(new Intent(ReadTagActivity.this, AssActivity.class));
                    }

//                mTextViewData.setText(payloadString);
                }
            }

            // Enable priority for current activity to detect scanned tags
            // enableForegroundDispatch( activity, pendingIntent, intentsFiltersArray, techListsArray );
            XELGlobalApplication.getInstance().getNfcAdapter(this).enableForegroundDispatch(this, mNfcPendingIntent, mReadTagFilters, null)
        }
        doResume()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            android.R.id.home ->
            {
                // todo: goto back activity from here
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun scanBarcode(strBarcode: String?) {

//        XELLogUtil.d_function(XELGlobalDefine.TAG, "scanBarcode : " + strBarcode);
    }

    protected fun readNFC(strNfc: String) {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "readNFC : $strNfc")
    }

    override fun onStop() {
        super.onStop()
        //        LogUtil.d(TAG, "Life onStop() " + this.getClass().getSimpleName());
        doStop()
    }

    override fun onPause() {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "onPause")

        // NFC Read
        if (selectedNFCReadMode == NFCReadMode.READ) {
            XELGlobalApplication.getInstance().getNfcAdapter(this).disableForegroundDispatch(this)
        }
        super.onPause()

//        LogUtil.d(TAG, "Life onPause() " + this.getClass().getSimpleName());
    }

    /*
     * This is called for activities that set launchMode to "singleTop" or
     * "singleTask" in their manifest package, or if a client used the
     * FLAG_ACTIVITY_SINGLE_TOP flag when calling startActivity(Intent).
     */
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

//        Log.d(TAG, "onNewIntent: " + intent);

        // Currently in tag READING mode
        if (intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            val msgs = getNdefMessagesFromIntent(intent)
            confirmDisplayedContentOverwrite(msgs!![0])
        } else if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
//            Toast.makeText(this, "This NFC tag has no NDEF data.", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * **** READING MODE METHODS ****
     */
    fun getNdefMessagesFromIntent(intent: Intent): Array<NdefMessage?>? {
        // Parse the intent
        var msgs: Array<NdefMessage?>? = null
        val action = intent.action
        if (action == NfcAdapter.ACTION_TAG_DISCOVERED || action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
            if (rawMsgs != null) {
                msgs = arrayOfNulls(rawMsgs.size)
                for (i in rawMsgs.indices) {
                    msgs[i] = rawMsgs[i] as NdefMessage
                }
            } else {
                // Unknown tag type
                val empty = byteArrayOf()
                val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
                val msg = NdefMessage(arrayOf(record))
                msgs = arrayOf(msg)
            }
        } else {
//            Log.e(TAG, "Unknown intent.");
            finish()
        }
        return msgs
    }

    // NFC 읽어졌을 때
    private fun confirmDisplayedContentOverwrite(msg: NdefMessage?) {
//        final String data = mTextViewData.getText().toString().trim();
//
//        mTextViewData.setText(new String(payload));

//        XELLogUtil.d_function(XELGlobalDefine.TAG, "confirmDisplayedContentOverwrite NFC Read : " + new String(msg.getRecords()[0].getPayload()));
        readNFC(String(msg!!.records[0].payload))

//        new AlertDialog.Builder(this)
//                .setTitle("New tag found!")
//                .setMessage("Do you wanna show the content of this tag?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // use the current values in the NDEF payload to update the text fields
//                        String payload = new String(msg.getRecords()[0].getPayload());
//
//                        mTextViewData.setText(new String(payload));
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        mTextViewData.setText(data);
//                        dialog.cancel();
//                    }
//                }).show();
    }

    override fun onDestroy() {
        super.onDestroy()

//        LogUtil.d(TAG, "Life onDestroy() " + this.getClass().getSimpleName());
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //        LogUtil.i(TAG, "onSaveInstanceState() : " + outState);
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        XELLogUtil.i(XELGlobalDefine.TAG, "onRestoreInstanceState() : $savedInstanceState")
        if (savedInstanceState != null) {
            XELLogUtil.e(XELGlobalDefine.TAG, "onRestoreInstanceState() is NOT Null")
            XELLogUtil.e(XELGlobalDefine.TAG, "App이 비정상으로 종료되어 App을 재시작합니다.")
            ActivityCompat.finishAffinity(this@XELActivity_Base)
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun finish() {
        super.finish()
        when (selectedPresetAnimation) {
            PresetAnimation.NONE -> {
            }
            PresetAnimation.SLIDE_RIGHT -> overridePendingTransition(
                R.anim.xel_anim_none,
                R.anim.xel_anim_slide_right_out
            )
            PresetAnimation.SLIDE_BOTTOM -> overridePendingTransition(
                R.anim.xel_anim_none,
                R.anim.xel_anim_slide_bottom_out
            )
            PresetAnimation.FADE -> overridePendingTransition(
                R.anim.xel_anim_none,
                R.anim.xel_anim_fade_out
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDataResponseSucess(tag: String, data: String) {}

    override fun onDataResponseError(tag: String, errorCode: Int) {}

    override fun onDataException(e: Exception) {}

    override fun onConfigurationChanged(newConfig: Configuration)
    {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE ->
            {
                displayLandscapeAfter()

                try {
                    if (XELSystemUtil.isDeviceNotchExist(this)) {
                        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
                        toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 50), 0, 0, 0)
                        if (XELSystemUtil.isActionBarBackButtonEnabled(this) == false) {
                            toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 100), 0, 0, 0)
                        }
                    }
                } catch (e: Exception) {
                    XELLogUtil.e(XELGlobalDefine.TAG, "", e)
                }
            }

            Configuration.ORIENTATION_PORTRAIT ->
            {
                displayPortraitAfter()

                try {
                    if (XELSystemUtil.isDeviceNotchExist(this))
                    {
                        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
                        toolbar.setPadding(0, 0, 0, 0)
                    }
                }
                catch (e: Exception)
                {
                    XELLogUtil.e(XELGlobalDefine.TAG, "", e)
                }
            }
        }
    }
}