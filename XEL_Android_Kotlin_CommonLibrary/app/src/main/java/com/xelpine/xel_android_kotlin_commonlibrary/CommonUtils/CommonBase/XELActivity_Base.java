//package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase;
//
//import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
//
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.nfc.NdefMessage;
//import android.nfc.NdefRecord;
//import android.nfc.NfcAdapter;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.ActivityCompat;
//import androidx.core.view.WindowCompat;
//
//import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication;
//import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine;
//import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil;
//import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELSystemUtil;
//import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils.XELVolleyUtil;
//import com.xelpine.xel_android_kotlin_commonlibrary.MainActivity;
//import com.xelpine.xel_android_kotlin_commonlibrary.R;
//
//
///**
// * 베이스 액티비티.
// *
// * 필수 : toolbar를 사용한다면 반드시 id가 toolbar여야 한다.
// */
//public abstract class XELActivity_Base extends AppCompatActivity implements XELVolleyUtil.XELVolleyResponseInterface
//{
//
//    // NFC Read
////    private NfcAdapter mNfcAdapter;
//    private PendingIntent mNfcPendingIntent;
//    IntentFilter[] mReadTagFilters;
//
//    /**
//     * NFC 읽기 모드 선택
//     */
//    public enum NFCReadMode
//    {
//        NONE,
//        READ
//    }
//
//    // 선택된 NFC 읽기 모드
//    private NFCReadMode selectedNFCReadMode = NFCReadMode.NONE;
//
//    /**
//     * 애니메이션과 Transition (setWindowAnimations) 의 경우, 트랜지션이 우선권을 가진다.
//     * 즉, 트랜지션을 구현 (setWindowAnimations에 구현 및 startActivity에 소스 추가) 시 애니메이션은 작동하지 않는다.
//     */
//
//    public enum PresetAnimation
//    {
//        NONE,
//        SLIDE_RIGHT,
//        SLIDE_BOTTOM,
//        FADE
//    }
//
//    // 선택된 애니메이션
//    private PresetAnimation selectedPresetAnimation = PresetAnimation.NONE;
//
//
//
//
//    /**
//     * 베이스 액티비티의 애니메이션 기본값 세팅
//     */
//    private void transAnimationSetting ()
//    {
//        // 킷캣 이상일 때, 스테이터스바 (최상단 시스템 바) 도 완전히 투명하게 보이도록 한다.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            w.setFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS, WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            w.setStatusBarColor(Color.TRANSPARENT);
//        }
//
//        switch (selectedPresetAnimation)
//        {
//            case NONE:
//                break;
//
//            case SLIDE_RIGHT:
//                overridePendingTransition(R.anim.xel_anim_slide_right_in, R.anim.xel_anim_none);
//                break;
//
//            case SLIDE_BOTTOM:
//                overridePendingTransition(R.anim.xel_anim_slide_bottom_in, R.anim.xel_anim_none);
//                break;
//
//            case FADE:
//                overridePendingTransition(R.anim.xel_anim_fade_in, R.anim.xel_anim_none);
//                break;
//        }
//    }
//
//
//
//    /**
//     * onCreate() 최상단에서 처리가 필요한 경우 사용. (ex : 윈도우 속성 변경 등..)
//     */
//    protected void beforeCreate() {
//
//        // 라이트모드에서는 라이트 네비게이션 바를 사용하도록
//        if (XELSystemUtil.getDarkMode(this) == false)
//        {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        }
//
////        getWindow().setStatusBarColor(getResources().getColor(R.color.duzonColor));
//
//        // 네비바 투명
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
//    }
//
//
//    /**
//     * Activity 초기화 루틴을 실제로 구현하는 부분.
//     *
//     * @param savedInstanceState
//     */
//    protected void doCreate(Bundle savedInstanceState) {
//
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "doCreate");
//
//    }
//
//    /**
//     * 프리셋 애니메이션 세팅. 애니메이션을 바꾸고 싶다면 해당 값을 오버라이드한다.
//     */
//    protected abstract PresetAnimation setPresetAnimation();
//
//    /**
//     * 트랜지션 세팅
//     * EnterTransition <--> ReturnTransition
//     * ExitTransition <--> ReenterTransition
//     */
//    protected abstract void setWindowTransitions();
//
//    /**
//     * NFC 읽기 모드 선택
//     * @return
//     */
//    protected abstract NFCReadMode setNFCReadMode();
//
//    /**
//     * 레이아웃 세팅. findViewById, setListener 등
//     */
//    protected abstract void initLayout();
//
//    /**
//     * initLayout 후 공통적으로 조절되어야 할 내역
//     */
//    protected void initLayoutAfter ()
//    {
//
//    }
//
//
//
//
//    /**
//     * 뷰가 윈도우에 연결될 때 호출된다.
//     * 그리기 시작하기 가능한 경우부터 이 메소드가 불린다.
//     */
//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//
//        Resources r = Resources.getSystem();
//        Configuration config = r.getConfiguration();
//        if( config.orientation == ORIENTATION_LANDSCAPE ) {
//
//            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttachedToWindow ORIENTATION_LANDSCAPE");
//
//            DisplayLandscapeAfter();
//
//            // 가로모드
//            try {
//
//                if (XELSystemUtil.isDeviceNotchExist(this))
//                {
//                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                    toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 50), 0, 0, 0);
//
//                    if (XELSystemUtil.isActionBarBackButtonEnabled(this) == false)
//                    {
//                        toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 100), 0, 0, 0);
//                    }
//                }
//
//            }
//            catch (Exception e)
//            {
//                XELLogUtil.e(XELGlobalDefine.TAG, "", e);
//            }
//        }
//        else {
//
//            XELLogUtil.d_function(XELGlobalDefine.TAG, "onAttachedToWindow ORIENTATION_PORTRAIT");
//
//            DisplayPortraitAfter ();
//
//            // 세로모드
//            try {
//                if (XELSystemUtil.isDeviceNotchExist(this)) {
//                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                    toolbar.setPadding(0, 0, 0, 0);
//                }
//            }
//            catch (Exception e)
//            {
//                XELLogUtil.e(XELGlobalDefine.TAG, "", e);
//            }
//        }
//    }
//
//    /**
//     * 기본값 세팅
//     */
//    protected abstract void initData();
//
//    /**
//     * 화면이 가로로 변했을 때 (진입 시, 진입 후 화면 전환 시 모두 다)
//     */
//    protected abstract void DisplayLandscapeAfter ();
//
//    /**
//     * 화면이 세로로 변했을 때 할 작업 (진입 시, 진입 후 화면 전환 시 모두 다)
//     */
//    protected abstract void DisplayPortraitAfter ();
//
//    /**
//     * onCreate의 모든 초기화 종료 후 추가 로직
//     */
//    protected abstract void initAfterLogic();
//
//    /**
//     * Activity가 pause 상태에 진입을 하고, 실제로 activity 가 pause call back을 처리해야하는 상황일때 호출한다.
//     */
//    protected abstract void doPause();
//
//    protected abstract void doResume();
//
//    protected abstract void doDestroy();
//
//
//
//
//    protected void doStart() {
//    }
//
//    protected void doStop() {
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if(savedInstanceState != null) {
//            XELLogUtil.e(XELGlobalDefine.TAG, "onCreate() is NOT Null");
//            XELLogUtil.e(XELGlobalDefine.TAG, "App이 비정상으로 종료되어 App을 재시작합니다.");
//            ActivityCompat.finishAffinity(XELActivity_Base.this);
//
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//
//            return;
//        }
//        else {
//
//            // 1. 프리셋 애니메이션 종류 선택
//            selectedPresetAnimation = this.setPresetAnimation();
//
//            if (selectedPresetAnimation == null)
//                selectedPresetAnimation = PresetAnimation.NONE;
//
//            // 2. 외부의 트랜지션 세팅. 해당 코드에서 트랜지션을 세팅하면 위의 애니메이션은 무시된다.
//            this.setWindowTransitions();
//
//            // 기본 애니메이션 세팅. 오버라이드 불가.
//            this.transAnimationSetting();
//
//            // 3. 윈도우 속성 변경 등
//            this.beforeCreate();
//
//            // 4. Activity Create 실행
//            this.doCreate(savedInstanceState);
//
//            /**
//             * NFC 로직 추가
//             */
//            selectedNFCReadMode = this.setNFCReadMode();
//
//            if (selectedNFCReadMode == null)
//                selectedNFCReadMode = NFCReadMode.NONE;
//
//            if (selectedNFCReadMode == NFCReadMode.READ)
//            {
//                NFCReadInit ();
//            }
//
//
//
//            // 5
//            this.initLayout();
//
//            // 5-1
//            this.initLayoutAfter();
//
//            // 6
//            this.initData();
//
//            // 7
//            this.initAfterLogic();
//        }
//
////        LogUtil.d(TAG, "Life onCreate() " + this.getClass().getSimpleName());
//    }
//
//    /**
//     * NFC 읽기모드의 초기화
//     */
//    private void NFCReadInit ()
//    {
////        mNfcAdapter = XELGlobalApplication.getInstance().getNfcAdapter(this);
//
//        XELGlobalApplication.getInstance().getNfcAdapter(this);
//
//        // Handle foreground NFC scanning in this activity by creating a
//        // PendingIntent with FLAG_ACTIVITY_SINGLE_TOP flag so each new scan
//        // is not added to the Back Stack
//        mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//
//        // Create intent filter to handle NDEF NFC tags detected from inside our
//        // application when in "read mode":
//        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
//        try {
//            ndefDetected.addDataType("application/com.fernandocejas.example.android.nfc");
//        } catch (IntentFilter.MalformedMimeTypeException e) {
//            throw new RuntimeException("Could not add MIME type.", e);
//        }
//
//        mReadTagFilters = new IntentFilter[] { ndefDetected };
//    }
//
//    @Override
//    protected final void onStart() {
//        super.onStart();
////        LogUtil.d(TAG, "Life onStart() " + this.getClass().getSimpleName());
//        doStart();
//    }
//
//    @Override
//    protected final void onResume() {
//        super.onResume();
//
////        LogUtil.d(TAG, "Life onResume() " + this.getClass().getSimpleName());
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "onResume");
//
//
//        // NFC Read
//        if (selectedNFCReadMode == NFCReadMode.READ) {
//            if (getIntent().getAction() != null) {
//                // tag received when app is not running and not in the foreground:
//                if (getIntent().getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
//                    NdefMessage[] msgs = getNdefMessagesFromIntent(getIntent());
//                    NdefRecord record = msgs[0].getRecords()[0];
//                    byte[] payload = record.getPayload();
//
//                    String payloadString = new String(payload);
//
//                    if (payloadString.equals("vader")) {
//                        finish();
////                	startActivity(new Intent(ReadTagActivity.this, VaderActivity.class));
//
//                    } else if (payloadString.equals("ass")) {
//                        finish();
////                	startActivity(new Intent(ReadTagActivity.this, AssActivity.class));
//                    }
//
////                mTextViewData.setText(payloadString);
//                }
//            }
//
//            // Enable priority for current activity to detect scanned tags
//            // enableForegroundDispatch( activity, pendingIntent, intentsFiltersArray, techListsArray );
//            XELGlobalApplication.getInstance().getNfcAdapter(this).enableForegroundDispatch(this, mNfcPendingIntent, mReadTagFilters, null);
//        }
//        doResume();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // todo: goto back activity from here
//                finish();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    protected void scanBarcode(String strBarcode) {
//
////        XELLogUtil.d_function(XELGlobalDefine.TAG, "scanBarcode : " + strBarcode);
//
//    }
//
//    protected void readNFC(String strNfc) {
//
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "readNFC : " + strNfc);
//
//    }
//
//    @Override
//    protected final void onStop() {
//        super.onStop();
////        LogUtil.d(TAG, "Life onStop() " + this.getClass().getSimpleName());
//        doStop();
//    }
//
//    @Override
//    protected final void onPause() {
//
//        XELLogUtil.d_function(XELGlobalDefine.TAG, "onPause");
//
//        // NFC Read
//        if (selectedNFCReadMode == NFCReadMode.READ){
//            XELGlobalApplication.getInstance().getNfcAdapter(this).disableForegroundDispatch(this);
//        }
//
//
//        super.onPause();
//
////        LogUtil.d(TAG, "Life onPause() " + this.getClass().getSimpleName());
//
//
//
//
//    }
//
//    /*
//     * This is called for activities that set launchMode to "singleTop" or
//     * "singleTask" in their manifest package, or if a client used the
//     * FLAG_ACTIVITY_SINGLE_TOP flag when calling startActivity(Intent).
//     */
//    @Override
//    protected void onNewIntent(Intent intent)
//    {
//        super.onNewIntent(intent);
//
////        Log.d(TAG, "onNewIntent: " + intent);
//
//        // Currently in tag READING mode
//        if (intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
//            NdefMessage[] msgs = getNdefMessagesFromIntent(intent);
//            confirmDisplayedContentOverwrite(msgs[0]);
//
//        } else if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
////            Toast.makeText(this, "This NFC tag has no NDEF data.", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    /*
//     * **** READING MODE METHODS ****
//     */
//    NdefMessage[] getNdefMessagesFromIntent(Intent intent) {
//        // Parse the intent
//        NdefMessage[] msgs = null;
//        String action = intent.getAction();
//        if (action.equals(NfcAdapter.ACTION_TAG_DISCOVERED) || action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
//            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//            if (rawMsgs != null){
//                msgs = new NdefMessage[rawMsgs.length];
//                for (int i = 0; i < rawMsgs.length; i++) {
//                    msgs[i] = (NdefMessage) rawMsgs[i];
//                }
//
//            } else {
//                // Unknown tag type
//                byte[] empty = new byte[] {};
//                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
//                NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
//                msgs = new NdefMessage[] { msg };
//            }
//
//        } else {
////            Log.e(TAG, "Unknown intent.");
//            finish();
//        }
//        return msgs;
//    }
//
//    // NFC 읽어졌을 때
//    private void confirmDisplayedContentOverwrite(final NdefMessage msg) {
////        final String data = mTextViewData.getText().toString().trim();
////
////        mTextViewData.setText(new String(payload));
//
////        XELLogUtil.d_function(XELGlobalDefine.TAG, "confirmDisplayedContentOverwrite NFC Read : " + new String(msg.getRecords()[0].getPayload()));
//
//        readNFC (new String(msg.getRecords()[0].getPayload()));
//
////        new AlertDialog.Builder(this)
////                .setTitle("New tag found!")
////                .setMessage("Do you wanna show the content of this tag?")
////                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int id) {
////                        // use the current values in the NDEF payload to update the text fields
////                        String payload = new String(msg.getRecords()[0].getPayload());
////
////                        mTextViewData.setText(new String(payload));
////                    }
////                })
////                .setNegativeButton("No", new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int id) {
////                        mTextViewData.setText(data);
////                        dialog.cancel();
////                    }
////                }).show();
//    }
//
//
//
//    @Override
//    protected final void onDestroy() {
//
//        super.onDestroy();
//
////        LogUtil.d(TAG, "Life onDestroy() " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
////        LogUtil.i(TAG, "onSaveInstanceState() : " + outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        XELLogUtil.i(XELGlobalDefine.TAG, "onRestoreInstanceState() : " + savedInstanceState);
//
//        if(savedInstanceState != null) {
//            XELLogUtil.e(XELGlobalDefine.TAG, "onRestoreInstanceState() is NOT Null");
//            XELLogUtil.e(XELGlobalDefine.TAG, "App이 비정상으로 종료되어 App을 재시작합니다.");
//            ActivityCompat.finishAffinity(XELActivity_Base.this);
//
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    @Override
//    public void finish() {
//        super.finish();
//
//        switch (selectedPresetAnimation)
//        {
//            case NONE:
//                break;
//
//            case SLIDE_RIGHT:
//                overridePendingTransition(R.anim.xel_anim_none, R.anim.xel_anim_slide_right_out);
//                break;
//
//            case SLIDE_BOTTOM:
//                overridePendingTransition(R.anim.xel_anim_none, R.anim.xel_anim_slide_bottom_out);
//                break;
//
//            case FADE:
//                overridePendingTransition(R.anim.xel_anim_none, R.anim.xel_anim_fade_out);
//                break;
//        }
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public void onDataResponseSucess(String tag, String data) {
//
//    }
//
//    @Override
//    public void onDataResponseError(String tag, int errorCode) {
//
//    }
//
//    @Override
//    public void onDataException(Exception e) {
//
//    }
//
//
//
//    @Override
//    public void onConfigurationChanged(@NonNull Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
//        switch(newConfig.orientation){
//
//            case ORIENTATION_LANDSCAPE:
//
//                DisplayLandscapeAfter();
//
//                try {
//                    if (XELSystemUtil.isDeviceNotchExist(this)) {
//                        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                        toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 50), 0, 0, 0);
//
//                        if (XELSystemUtil.isActionBarBackButtonEnabled(this) == false)
//                        {
//                            toolbar.setPadding(XELSystemUtil.getStatusBarHeight(this, 100), 0, 0, 0);
//                        }
//                    }
//                }
//                catch (Exception e)
//                {
//                    XELLogUtil.e(XELGlobalDefine.TAG, "", e);
//                }
//
//
//                break;
//
//            case Configuration.ORIENTATION_PORTRAIT:
//
//                DisplayPortraitAfter();
//
//                try {
//                    if (XELSystemUtil.isDeviceNotchExist(this)) {
//                        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//                        toolbar.setPadding(0, 0, 0, 0);
//                    }
//                }
//                catch (Exception e)
//                {
//                    XELLogUtil.e(XELGlobalDefine.TAG, "", e);
//                }
//
//                break;
//
//        }
//    }
//}