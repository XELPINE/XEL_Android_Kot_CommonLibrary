package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.lang.Exception
import java.util.*

/**
 * @author : 김진용
 * @brief : 커스텀 다이얼로그
 * @date : 2012. 7. 25.
 * @modify : 다이얼로그 이미지는 커스텀하게 적용...
 */
class XELDialogUtil {

    companion object
    {
        var dlg: Dialog? = null
        var dialogBuilder: AlertDialog.Builder? = null

        // 확인, 취소버튼
        fun Dialog_OkAndCancel(
            context: Context?,
            msg: String?,
            clickOKListener: View.OnClickListener?,
            clickCancelListener: View.OnClickListener?
        ) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            dlg = Dialog(context!!, R.style.Theme_Dialog)
            dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (XELDisplayUtil.isTablet(context)) {
                dlg!!.setContentView(R.layout.xel_dlg_custom_large)
                (dlg!!.findViewById<View>(R.id.popup_edittext) as EditText).inputType =
                    0
            } else {
                dlg!!.setContentView(R.layout.xel_dlg_custom)
            }
            dlg!!.setCancelable(false)
            val tv_msg = dlg!!.findViewById<View>(R.id.popup_msg) as TextView
            val btnPositive = dlg!!.findViewById<View>(R.id.btn_popup_ok) as Button
            val btnNative = dlg!!.findViewById<View>(R.id.btn_popup_cancel) as Button
            val btnClose = dlg!!.findViewById<View>(R.id.popup_close) as Button
            btnClose.setOnClickListener {
                dlg!!.dismiss()
                dlg = null
            }
            tv_msg.text = msg
            btnPositive.setOnClickListener(clickOKListener)
            btnNative.setOnClickListener(clickCancelListener)
            dlg!!.show()
        }

        // 확인버튼
        fun Dialog_OkOnly(context: Context?, msg: String?, clickOKListener: View.OnClickListener?) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            dlg = Dialog(context!!, R.style.Theme_Dialog)
            dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (XELDisplayUtil.isTablet(context)) {
                dlg!!.setContentView(R.layout.xel_dlg_custom_large)
                (dlg!!.findViewById<View>(R.id.popup_edittext) as EditText).inputType =
                    0
            } else {
                dlg!!.setContentView(R.layout.xel_dlg_custom)
            }
            dlg!!.setCancelable(false)
            val tv_msg = dlg!!.findViewById<View>(R.id.popup_msg) as TextView
            val btnPositive = dlg!!.findViewById<View>(R.id.btn_popup_ok) as Button
            val btnNative = dlg!!.findViewById<View>(R.id.btn_popup_cancel) as Button
            val btnClose = dlg!!.findViewById<View>(R.id.popup_close) as Button
            btnClose.setOnClickListener {
                dlg!!.dismiss()
                dlg = null
            }
            btnNative.visibility = View.GONE
            //        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
            tv_msg.text = msg
            btnPositive.setOnClickListener(clickOKListener)
            dlg!!.show()

//        Window window = dlg.getWindow();
//        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        }

        // 확인버튼(text를 바꿀 수 있도록)
        fun Dialog_OkOnlyText(
            context: Context,
            msg: String,
            textok: String?,
            clickOKListener: View.OnClickListener?
        ) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            dlg = Dialog(context, R.style.Theme_Dialog)
            dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (XELDisplayUtil.isTablet(context)) {
                dlg!!.setContentView(R.layout.xel_dlg_custom_large)
                val hideEdit = dlg!!.findViewById<View>(R.id.popup_edittext) as EditText
                hideEdit.inputType = 0
                hideEdit.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
                    if (keyEvent.action == KeyEvent.ACTION_UP && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        val barcode = (view as EditText).text.toString()
                        view.setText("")
                        val sendIntent = Intent("douzone.SEND_BROAD_CAST")
                        sendIntent.putExtra("scanbarcode", barcode)
                        context.sendBroadcast(sendIntent)
                        return@OnKeyListener false
                    }
                    false
                })
            } else {
                dlg!!.setContentView(R.layout.xel_dlg_custom)
            }
            dlg!!.setCancelable(false)
            val tv_msg = dlg!!.findViewById<View>(R.id.popup_msg) as TextView
            val btnPositive = dlg!!.findViewById<View>(R.id.btn_popup_ok) as Button
            val btnNative = dlg!!.findViewById<View>(R.id.btn_popup_cancel) as Button
            val btnClose = dlg!!.findViewById<View>(R.id.popup_close) as Button
            btnClose.setOnClickListener {
                dlg!!.dismiss()
                dlg = null
            }
            btnNative.visibility = View.GONE
            //        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
            tv_msg.text = msg.replace("\\u00a0".toRegex(), " ")
            btnPositive.setOnClickListener(clickOKListener)
            btnPositive.text = textok
            dlg!!.show()
        }

        //    // 확인버튼(text를 바꿀 수 있도록)
        //    public static void Dialog(Context context, String msg, String textok, final View.OnClickListener clickOKListener, final View.OnClickListener clickCloseListener) {
        //        if (dlg != null) {
        //            dlg.dismiss();
        //            dlg = null;
        //        }
        //
        //        dlg = new Dialog(context, R.style.Theme_Dialog);
        //        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //        if (XELDisplayUtil.isTablet(context)) {
        //            dlg.setContentView(R.layout.dlg_custom_large);
        //            ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
        //        } else {
        //            dlg.setContentView(R.layout.dlg_custom);
        //        }
        //        dlg.setCancelable(false);
        //        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        //        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        //        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        //        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        //        btnNative.setVisibility(View.GONE);
        //        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
        //        tv_msg.setText(msg);
        //        btnPositive.setOnClickListener(clickOKListener);
        //        btnPositive.setText(textok);
        //        btnClose.setOnClickListener(clickCloseListener);
        //        dlg.show();
        //    }
        //
        //    // 확인버튼(text를 바꿀 수 있도록, close 버튼 유무 추가)
        //    public static void Dialog(Context context, String msg, String textok, boolean value, final View.OnClickListener clickOKListener) {
        //        if (dlg != null) {
        //            dlg.dismiss();
        //            dlg = null;
        //        }
        //
        //        dlg = new Dialog(context, R.style.Theme_Dialog);
        //        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //        if (XELDisplayUtil.isTablet(context)) {
        //            dlg.setContentView(R.layout.dlg_custom_large);
        //            ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
        //        } else {
        //            dlg.setContentView(R.layout.dlg_custom);
        //        }
        //        dlg.setCancelable(false);
        //        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        //        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        //        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        //        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        //        if (value) {
        //            btnClose.setVisibility(View.VISIBLE);
        //        } else {
        //            btnClose.setVisibility(View.GONE);
        //        }
        //        btnClose.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                dlg.dismiss();
        //                dlg = null;
        //            }
        //        });
        //        btnNative.setVisibility(View.GONE);
        //        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
        //        tv_msg.setText(msg);
        //        btnPositive.setOnClickListener(clickOKListener);
        //        btnPositive.setText(textok);
        //        dlg.show();
        //    }
        // 확인,취소버튼(text를 바꿀 수 있도록)
        fun Dialog_OkAndCancelText(
            context: Context?,
            msg: String?,
            textok: String?,
            textcancel: String?,
            clickOKListener: View.OnClickListener?,
            clickCancelListener: View.OnClickListener?
        ) {
            try {
                if (dlg != null) {
                    dlg!!.dismiss()
                    dlg = null
                }
                dlg = Dialog(context!!, R.style.Theme_Dialog)
                dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                if (XELDisplayUtil.isTablet(context)) {
                    dlg!!.setContentView(R.layout.xel_dlg_custom_large)
                    (dlg!!.findViewById<View>(R.id.popup_edittext) as EditText).inputType =
                        0
                } else {
                    dlg!!.setContentView(R.layout.xel_dlg_custom)
                }
                dlg!!.setCancelable(false)
                val tv_msg = dlg!!.findViewById<View>(R.id.popup_msg) as TextView
                val btnPositive = dlg!!.findViewById<View>(R.id.btn_popup_ok) as Button
                val btnNative = dlg!!.findViewById<View>(R.id.btn_popup_cancel) as Button
                val btnClose = dlg!!.findViewById<View>(R.id.popup_close) as Button
                btnClose.setOnClickListener {
                    dlg!!.dismiss()
                    dlg = null
                }
                tv_msg.text = msg
                btnPositive.setOnClickListener(clickOKListener)
                btnNative.setOnClickListener(clickCancelListener)
                btnPositive.text = textok
                btnNative.text = textcancel
                dlg!!.show()
            } catch (e: Exception) {
            }
        }

        private var second: TimerTask? = null
        fun closeWait(waitingTime: Int) {
            second = object : TimerTask() {
                override fun run() {
                    if (null != dlg) {
                        dlg!!.dismiss()
                        dlg = null
                    }
                }
            }
            val timer = Timer()
            timer.schedule(second, waitingTime.toLong())
        }

        // 확인,취소버튼(text를 바꿀 수 있도록) + 타이머
        fun Dialog_OkAndCancelWithTimer(
            context: Context?,
            msg: String?,
            textok: String?,
            textcancel: String?,
            clickOKListener: View.OnClickListener?,
            clickCancelListener: View.OnClickListener?,
            waitingTime: Int
        ) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            dlg = Dialog(context!!, R.style.Theme_Dialog)
            dlg!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dlg!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (XELDisplayUtil.isTablet(context)) {
                dlg!!.setContentView(R.layout.xel_dlg_custom_large)
                (dlg!!.findViewById<View>(R.id.popup_edittext) as EditText).inputType =
                    0
            } else {
                dlg!!.setContentView(R.layout.xel_dlg_custom)
            }
            dlg!!.setCancelable(false)
            val tv_msg = dlg!!.findViewById<View>(R.id.popup_msg) as TextView
            val btnPositive = dlg!!.findViewById<View>(R.id.btn_popup_ok) as Button
            val btnNative = dlg!!.findViewById<View>(R.id.btn_popup_cancel) as Button
            val btnClose = dlg!!.findViewById<View>(R.id.popup_close) as Button
            btnClose.setOnClickListener {
                dlg!!.dismiss()
                dlg = null
            }
            tv_msg.text = msg
            btnPositive.setOnClickListener(clickOKListener)
            btnNative.setOnClickListener(clickCancelListener)
            btnPositive.text = textok
            btnNative.text = textcancel
            closeWait(waitingTime)
            dlg!!.show()
        }
        //    /**
        //     * 로딩 다이얼로그 시작
        //     * @param context
        //     */
        //    public static void LoadingDialog (Context context)
        //    {
        //        if (dlg != null) {
        //            dlg.dismiss();
        //            dlg = null;
        //        }
        //
        //        dlg = new Dialog(context, R.style.Theme_Dialog);
        ////        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //        dlg.setContentView(R.layout.dialog_loading);
        //        dlg.setCancelable(false);
        //
        //        dlg.show();
        //    }
        /**
         * 로딩 다이얼로그 시작
         * @param context
         */
        fun LoadingDialog(context: Context?) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }

//        dlg = new Dialog(context, R.style.Theme_Dialog);
////        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dlg.setContentView(R.layout.dialog_loading);
//        dlg.setCancelable(false);
//
//        dlg.show();
            val materialAlertDialogBuilder = MaterialAlertDialogBuilder(context!!)
            materialAlertDialogBuilder
                .setView(R.layout.xel_dlg_loading)
                .setCancelable(false)
            dlg = materialAlertDialogBuilder.create()
            dlg!!.show()
        }

        /**
         * 메테리얼 디자인 다이얼로그
         */
        fun MaterialDialog_OkAndCancelText(
            context: Context?,
            title: String?,
            message: String?,
            positiveButtonTitle: String?,
            negativeButtonTitle: String?,
            clickOKListener: DialogInterface.OnClickListener?,
            clickCancelListener: DialogInterface.OnClickListener?
        ) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            val materialAlertDialogBuilder =
                MaterialAlertDialogBuilder(context!!, R.style.XEL__MaterialAlertDialog)
            materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle, clickOKListener)
                .setNegativeButton(negativeButtonTitle, clickCancelListener)
                .setCancelable(false)
            dlg = materialAlertDialogBuilder.create()
            dlg!!.show()
        }

        fun MaterialDialog_OkOnlyText(
            context: Context?,
            title: String?,
            message: String?,
            positiveButtonTitle: String?,
            clickOKListener: DialogInterface.OnClickListener?
        ) {
            if (dlg != null) {
                dlg!!.dismiss()
                dlg = null
            }
            val materialAlertDialogBuilder =
                MaterialAlertDialogBuilder(context!!, R.style.XEL__MaterialAlertDialog)
            materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle, clickOKListener)
                .setCancelable(false)
            dlg = materialAlertDialogBuilder.create()
            dlg!!.show()
        }

        interface onEditDlgClickListener {
            fun onClick(v: View?, etEdit: EditText?)
        }
    }


}
