package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xelpine.xel_android_kotlin_commonlibrary.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : 김진용
 * @brief : 커스텀 다이얼로그
 * @date : 2012. 7. 25.
 * @modify : 다이얼로그 이미지는 커스텀하게 적용...
 */
public class XELDialogUtil {
    public static Dialog dlg = null;
    public static AlertDialog.Builder dialogBuilder;

    // 확인, 취소버튼
    public static void Dialog_OkAndCancel(Context context, String msg, final View.OnClickListener clickOKListener, final View.OnClickListener clickCancelListener) {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

        dlg = new Dialog(context, R.style.Theme_Dialog);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (XELDisplayUtil.isTablet(context)) {
            dlg.setContentView(R.layout.xel_dlg_custom_large);
            ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
        } else {
            dlg.setContentView(R.layout.xel_dlg_custom);
        }
        dlg.setCancelable(false);
        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                dlg = null;
            }
        });
        tv_msg.setText(msg);
        btnPositive.setOnClickListener(clickOKListener);
        btnNative.setOnClickListener(clickCancelListener);
        dlg.show();
    }

    // 확인버튼
    public static void Dialog_OkOnly(Context context, String msg, final View.OnClickListener clickOKListener) {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

        dlg = new Dialog(context, R.style.Theme_Dialog);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (XELDisplayUtil.isTablet(context)) {
            dlg.setContentView(R.layout.xel_dlg_custom_large);
            ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
        } else {
            dlg.setContentView(R.layout.xel_dlg_custom);
        }
        dlg.setCancelable(false);
        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                dlg = null;
            }
        });
        btnNative.setVisibility(View.GONE);
//        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
        tv_msg.setText(msg);
        btnPositive.setOnClickListener(clickOKListener);
        dlg.show();

//        Window window = dlg.getWindow();
//        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
    }

    // 확인버튼(text를 바꿀 수 있도록)
    public static void Dialog_OkOnlyText(final Context context, String msg, String textok, final View.OnClickListener clickOKListener) {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }


        dlg = new Dialog(context, R.style.Theme_Dialog);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (XELDisplayUtil.isTablet(context)) {
            dlg.setContentView(R.layout.xel_dlg_custom_large);
            EditText hideEdit = (EditText) dlg.findViewById(R.id.popup_edittext);
            hideEdit.setInputType(0);
            hideEdit.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if ((keyEvent.getAction() == KeyEvent.ACTION_UP) && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                        String barcode = ((EditText)view).getText().toString();
                        ((EditText)view).setText("");

                        Intent sendIntent = new Intent("douzone.SEND_BROAD_CAST");
                        sendIntent.putExtra("scanbarcode", barcode);
                        context.sendBroadcast(sendIntent);
                        return false;
                    }
                    return false;
                }
            });
        } else {
            dlg.setContentView(R.layout.xel_dlg_custom);
        }
        dlg.setCancelable(false);
        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                dlg = null;
            }
        });
        btnNative.setVisibility(View.GONE);
//        btnPositive.setBackgroundResource(R.drawable.btn_asset_code_input_bottom_selector);
        tv_msg.setText(msg.replaceAll("\\u00a0", " "));
        btnPositive.setOnClickListener(clickOKListener);
        btnPositive.setText(textok);

        dlg.show();
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
    public static void Dialog_OkAndCancelText(Context context, String msg, String textok, String textcancel, final View.OnClickListener clickOKListener, final View.OnClickListener clickCancelListener) {
        try {
            if (dlg != null) {
                dlg.dismiss();
                dlg = null;
            }

            dlg = new Dialog(context, R.style.Theme_Dialog);
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (XELDisplayUtil.isTablet(context)) {
                dlg.setContentView(R.layout.xel_dlg_custom_large);
                ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
            } else {
                dlg.setContentView(R.layout.xel_dlg_custom);
            }
            dlg.setCancelable(false);
            TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
            Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
            Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
            Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dlg.dismiss();
                    dlg = null;
                }
            });
            tv_msg.setText(msg);
            btnPositive.setOnClickListener(clickOKListener);
            btnNative.setOnClickListener(clickCancelListener);
            btnPositive.setText(textok);
            btnNative.setText(textcancel);
            dlg.show();
        } catch (Exception e) {
        }
    }

    private static TimerTask second;

    public static void closeWait(final int waitingTime) {

        second = new TimerTask() {

            @Override
            public void run() {
                if (null != dlg) {
                    dlg.dismiss();
                    dlg = null;
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, waitingTime);
    }

    // 확인,취소버튼(text를 바꿀 수 있도록) + 타이머
    public static void Dialog_OkAndCancelWithTimer(Context context, String msg, String textok, String textcancel, final View.OnClickListener clickOKListener, final View.OnClickListener clickCancelListener, int waitingTime) {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

        dlg = new Dialog(context, R.style.Theme_Dialog);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (XELDisplayUtil.isTablet(context)) {
            dlg.setContentView(R.layout.xel_dlg_custom_large);
            ((EditText)dlg.findViewById(R.id.popup_edittext)).setInputType(0);
        } else {
            dlg.setContentView(R.layout.xel_dlg_custom);
        }
        dlg.setCancelable(false);
        TextView tv_msg = (TextView) dlg.findViewById(R.id.popup_msg);
        Button btnPositive = (Button) dlg.findViewById(R.id.btn_popup_ok);
        Button btnNative = (Button) dlg.findViewById(R.id.btn_popup_cancel);
        Button btnClose = (Button) dlg.findViewById(R.id.popup_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
                dlg = null;
            }
        });
        tv_msg.setText(msg);
        btnPositive.setOnClickListener(clickOKListener);
        btnNative.setOnClickListener(clickCancelListener);
        btnPositive.setText(textok);
        btnNative.setText(textcancel);

        closeWait(waitingTime);

        dlg.show();
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
    public static void LoadingDialog (Context context)
    {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

//        dlg = new Dialog(context, R.style.Theme_Dialog);
////        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dlg.setContentView(R.layout.dialog_loading);
//        dlg.setCancelable(false);
//
//        dlg.show();

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);

        materialAlertDialogBuilder
                .setView(R.layout.xel_dlg_loading)
                .setCancelable(false);

        dlg = materialAlertDialogBuilder.create();

        dlg.show();
    }

    public interface onEditDlgClickListener {
        void onClick(View v, EditText etEdit);
    }


    /**
     * 메테리얼 디자인 다이얼로그
     */
    public static void MaterialDialog_OkAndCancelText(
            Context context,
            String title,
            String message,
            String positiveButtonTitle,
            String negativeButtonTitle,
            final DialogInterface.OnClickListener clickOKListener,
            final DialogInterface.OnClickListener clickCancelListener)
    {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.XEL__MaterialAlertDialog);

        materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle, clickOKListener)
                .setNegativeButton(negativeButtonTitle, clickCancelListener)
                .setCancelable(false);

        dlg = materialAlertDialogBuilder.create();

        dlg.show();
    }

    public static void MaterialDialog_OkOnlyText(
            Context context,
            String title,
            String message,
            String positiveButtonTitle,
            final DialogInterface.OnClickListener clickOKListener)
    {
        if (dlg != null) {
            dlg.dismiss();
            dlg = null;
        }

        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.XEL__MaterialAlertDialog);

        materialAlertDialogBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle, clickOKListener)
                .setCancelable(false);

        dlg = materialAlertDialogBuilder.create();

        dlg.show();
    }
}
