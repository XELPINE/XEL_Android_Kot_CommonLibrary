package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.lang.Exception
import java.nio.charset.Charset

class Activity_NFCWrite : XELActivity_Base()
{
    // NFC-related variables
    private var mNfcAdapter: NfcAdapter? = null
    private var mNfcPendingIntent: PendingIntent? = null
    private lateinit var mWriteTagFilters: Array<IntentFilter>
    private var mWriteMode = false

    private var mImageViewImage: ImageView? = null
    private var mEditTextData: EditText? = null
    private var mButtonWrite: Button? = null

    protected override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_nfcwrite)
        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_NFCWrite doCreate")
    }

    protected override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.SLIDE_RIGHT
    }

    protected override fun setWindowTransitions() {}

    protected override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    protected override fun initLayout() {
        mImageViewImage = findViewById(R.id.image) as ImageView?
        mEditTextData = findViewById(R.id.textData) as EditText?
        mButtonWrite = findViewById(R.id.buttonWriteTag) as Button?
        mButtonWrite!!.setOnClickListener(mTagWriter)
    }

    protected override fun initData() {
        // get an instance of the context's cached NfcAdapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // if null is returned this demo cannot run. Use this check if the
        // "required" parameter of <uses-feature> in the manifest is not set
        // NFC 지원이 가능한 기기인지 확인.
        if (mNfcAdapter == null) {
            Toast.makeText(
                this,
                "Your device does not support NFC. Cannot run demo.",
                Toast.LENGTH_LONG
            ).show()
            finish()
            return
        }
    }

    protected override fun displayLandscapeAfter() {}

    protected override fun displayPortraitAfter() {}

    protected override fun initAfterLogic() {

        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_NFCWrite initAfterLogic")

        // check if NFC is enabled
        // NFC 켜짐 여부 확인
        checkNfcEnabled()

        // Handle foreground NFC scanning in this activity by creating a
        // PendingIntent with FLAG_ACTIVITY_SINGLE_TOP flag so each new scan
        // is not added to the Back Stack
        // PendingIntent 생성
        mNfcPendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            0
        )

        // Create intent filter to detect any NFC tag when attempting to write
        // to a tag in "write mode"
        // 인텐트 필터 추가
        val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)

        // create IntentFilter arrays:
        mWriteTagFilters = arrayOf(tagDetected)
    }

    /*
     * This is called for activities that set launchMode to "singleTop" or
     * "singleTask" in their manifest package, or if a client used the
     * FLAG_ACTIVITY_SINGLE_TOP flag when calling startActivity(Intent).
     */
    protected override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        XELLogUtil.d_function(XELGlobalDefine.TAG, "onNewIntent: $intent")
        if (mWriteMode) {
            // Currently in tag WRITING mode
            if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
                val detectedTag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                writeTag(buildNdefMessage(), detectedTag)
                mImageViewImage!!.setImageDrawable(getResources().getDrawable(R.mipmap.android_blue_logo))
                mEditTextData!!.isEnabled = true
            }
        }
    }

    /*
     * **** WRITING MODE METHODS ****
     */

    /*
     * **** WRITING MODE METHODS ****
     */
    private val mTagWriter = View.OnClickListener {
        val snackbar = Snackbar
            .make(
                (findViewById(R.id.coor) as CoordinatorLayout?)!!,
                "XEL SNACKBAR TEST",
                Snackbar.LENGTH_LONG
            )
        snackbar.show()
        if (mEditTextData!!.text.toString().trim { it <= ' ' }.length == 0) {
            Toast.makeText(
                this@Activity_NFCWrite,
                "The data to write is empty. Please fill it!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            enableTagWriteMode()
        }
    }

    private fun enableTagWriteMode() {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "enableTagWriteMode (NFC 쓰기모드 켜기)")
        mWriteMode = true
        mNfcAdapter!!.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null)
        mImageViewImage!!.setImageDrawable(getResources().getDrawable(R.mipmap.android_writing_logo))
        mEditTextData!!.isEnabled = false
    }

    fun writeTag(message: NdefMessage, tag: Tag?): Boolean {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "writeTag (태그 쓰기) : $message")
        val size = message.toByteArray().size
        try {
            val ndef = Ndef.get(tag)
            if (ndef != null) {
                ndef.connect()
                if (!ndef.isWritable) {
                    Toast.makeText(
                        this,
                        "Cannot write to this tag. This tag is read-only. (읽기전용임)",
                        Toast.LENGTH_LONG
                    ).show()
                    return false
                }
                if (ndef.maxSize < size) {
//                    Toast.makeText(this, "Cannot write to this tag. Message size (" + size
//                            + " bytes) exceeds this tag's capacity of "
//                            + ndef.getMaxSize() + " bytes. (용량오버)", Toast.LENGTH_LONG).show();
                    Toast.makeText(
                        this, "태그에 쓸 수 없습니다. 메시지 크기 (" + size
                                + " bytes) 가 태그의 용량 ("
                                + ndef.maxSize + " ) bytes 보다 큽니다. (용량오버)", Toast.LENGTH_LONG
                    ).show()
                    return false
                }
                ndef.writeNdefMessage(message)
                Toast.makeText(this, "태그에 쓰기 성공했습니다. (쓰기성공)", Toast.LENGTH_LONG).show()
                return true
            }
            Toast.makeText(
                this,
                "이 태그에 쓸 수 없습니다. 이 태그는 NDEF를 미지원합니다. (쓰기실패, NDEF 미지원)",
                Toast.LENGTH_LONG
            ).show()
            return false
        } catch (e: Exception) {
            XELLogUtil.e(XELGlobalDefine.TAG, "writeTag ERROR : ", e)
            Toast.makeText(this, "태그 쓰기 중 에러가 발생했습니다. (예외)", Toast.LENGTH_LONG).show()
        }
        return false
    }

    private fun buildNdefMessage(): NdefMessage {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "buildNdefMessage (NDEF 메시지로 변환)")

        // get the values from the form's text fields:
        val data = mEditTextData!!.text.toString().trim { it <= ' ' }

        // create a new NDEF record and containing NDEF message using the app's custom MIME type:
        val mimeType = "application/com.fernandocejas.example.android.nfc"
        val mimeBytes =
            mimeType.toByteArray(Charset.forName("UTF-8"))
        val dataBytes =
            data.toByteArray(Charset.forName("UTF-8"))
        val id = ByteArray(0)
        val record = NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, id, dataBytes)

        // return the NDEF message
        return NdefMessage(arrayOf(record))
    }

    /*
     * **** HELPER METHODS ****
     */

    /*
     * **** HELPER METHODS ****
     */
    private fun checkNfcEnabled() {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "checkNfcEnabled (NFC 활성화 체크)")
        val nfcEnabled = mNfcAdapter!!.isEnabled
        if (!nfcEnabled) {
//            new AlertDialog.Builder(Activity_NFCWrite.this)
//                    .setTitle("TITLE")
//                    .setMessage("NFC가 꺼져있습니다. 해당 기능을 켜주십시오.")
//                    .setCancelable(false)
//                    .setPositiveButton(getString(R.string.app_name),
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id){
//                                    startActivity(new Intent( android.provider.Settings.ACTION_WIRELESS_SETTINGS));
//                                }
//                            }).create().show();
            XELDialogUtil.Dialog_OkOnly(this, "NFC가 꺼져있습니다. 해당 기능을 켜주십시오.",
                View.OnClickListener { startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) })
        } else {
            XELDialogUtil.closeWait(0)
        }
        XELLogUtil.d_function(
            XELGlobalDefine.TAG,
            "===== checkNfcEnabled (NFC 활성화 체크 결과) : $nfcEnabled"
        )
    }

    protected override fun doPause() {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "doPause")
        //        Log.d(TAG, "onPause: " + getIntent());
        mNfcAdapter!!.disableForegroundDispatch(this)
    }

    protected override fun doResume() {
        XELLogUtil.d_function(XELGlobalDefine.TAG, "doResume")

        // Double check if NFC is enabled
        checkNfcEnabled()
    }

    override fun doStart() {
    }

    override fun doStop() {
    }

    protected override fun doDestroy() {}
}