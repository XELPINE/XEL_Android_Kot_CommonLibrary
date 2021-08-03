package com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode

import android.app.Activity
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentFilter.MalformedMimeTypeException
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.lang.RuntimeException

class Activity_NFCRead : Activity() {
    // NFC-related variables
    private var mNfcAdapter: NfcAdapter? = null
    private var mNfcPendingIntent: PendingIntent? = null
    lateinit var mReadTagFilters: Array<IntentFilter>
    private var mTextViewData: TextView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfcread)
        mTextViewData = findViewById<View>(R.id.textData) as TextView

        // get an instance of the context's cached NfcAdapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        // if null is returned this demo cannot run. Use this check if the
        // "required" parameter of <uses-feature> in the manifest is not set
        if (mNfcAdapter == null) {
            Toast.makeText(
                this,
                "Your device does not support NFC. Cannot run demo.",
                Toast.LENGTH_LONG
            ).show()
            finish()
            return
        }

        // check if NFC is enabled
        checkNfcEnabled()

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

    /* Called when the activity will start interacting with the user. */
    override fun onResume() {
        super.onResume()

        // Double check if NFC is enabled
        checkNfcEnabled()
        Log.d(TAG, "onResume: $intent")
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
                mTextViewData!!.text = payloadString
            }
        }

        // Enable priority for current activity to detect scanned tags
        // enableForegroundDispatch( activity, pendingIntent, intentsFiltersArray, techListsArray );
        mNfcAdapter!!.enableForegroundDispatch(this, mNfcPendingIntent, mReadTagFilters, null)
    }

    /* Called when the system is about to start resuming a previous activity. */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: $intent")
        mNfcAdapter!!.disableForegroundDispatch(this)
    }

    /*
     * This is called for activities that set launchMode to "singleTop" or
     * "singleTask" in their manifest package, or if a client used the
     * FLAG_ACTIVITY_SINGLE_TOP flag when calling startActivity(Intent).
     */
    override fun onNewIntent(intent: Intent) {
        Log.d(TAG, "onNewIntent: $intent")

        // Currently in tag READING mode
        if (intent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
            val msgs = getNdefMessagesFromIntent(intent)
            confirmDisplayedContentOverwrite(msgs!![0])
        } else if (intent.action == NfcAdapter.ACTION_TAG_DISCOVERED) {
            Toast.makeText(this, "This NFC tag has no NDEF data.", Toast.LENGTH_LONG).show()
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
            Log.e(TAG, "Unknown intent.")
            finish()
        }
        return msgs
    }

    private fun confirmDisplayedContentOverwrite(msg: NdefMessage?) {
        val data = mTextViewData!!.text.toString().trim { it <= ' ' }
        AlertDialog.Builder(this)
            .setTitle("New tag found!")
            .setMessage("Do you wanna show the content of this tag?")
            .setPositiveButton("Yes") { dialog, id -> // use the current values in the NDEF payload to update the text fields
                val payload = String(msg!!.records[0].payload)
                mTextViewData!!.text = payload
            }
            .setNegativeButton("No") { dialog, id ->
                mTextViewData!!.text = data
                dialog.cancel()
            }.show()
    }

    /*
     * **** HELPER METHODS ****
     */
    private fun checkNfcEnabled() {
        val nfcEnabled = mNfcAdapter!!.isEnabled
        if (!nfcEnabled) {
            AlertDialog.Builder(this@Activity_NFCRead)
                .setTitle("text_warning_nfc_is_off")
                .setMessage("text_turn_on_nfc")
                .setCancelable(false)
                .setPositiveButton(
                    getString(R.string.app_name)
                ) { dialog, id -> startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS)) }
                .create().show()
        }
    }

    companion object {
        private val TAG = Activity_NFCRead::class.java.simpleName
    }
}
