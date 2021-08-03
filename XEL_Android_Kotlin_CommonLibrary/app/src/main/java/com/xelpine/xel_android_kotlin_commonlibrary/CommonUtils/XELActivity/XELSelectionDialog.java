package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter.XELSelectionDialogAdapter;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil;
import com.xelpine.xel_android_kotlin_commonlibrary.R;

import java.util.ArrayList;

/**
 * 선택 다이얼로그.
 * 하지만 StatusBar가 검은색이 되는 문제가 남는다.
 */
public class XELSelectionDialog {


    private XELSelectionDialogAdapter xelSelectionDialogAdapter;
    private Activity cxt;

    private onItemSelectListener mItemSelectListener;
    public interface onItemSelectListener{
        void onItemSelect(int position, int requestId);
    }

    public XELSelectionDialog(Activity context){
        cxt = context;

    }

    public void showDialog(final int requestId, final ArrayList<? extends XELCommonSelectionInterface> _dataList, onItemSelectListener listener) {
        showDialog(requestId, _dataList, listener, -1);
    }

//    public void showDialog(final int requestId, final Object _dataList, onItemSelectListener listener, int selectedPosition) {
//        showDialog(requestId, _dataList, listener, -1);
//    }

    // 선택창
    public void showDialog(final int requestId, final ArrayList<? extends XELCommonSelectionInterface> _dataList, onItemSelectListener listener, int selectedPosition) {

        final View innerView = cxt.getLayoutInflater().inflate(R.layout.xel_selectondialog, null);
        mItemSelectListener = listener;

//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            ((View)innerView.findViewById(R.id.empty_view)).setVisibility(View.GONE);
//        }

        final Dialog mDialog = new Dialog(cxt);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(innerView);

        RecyclerView xel_selectionDialog_rv_list = (RecyclerView) innerView.findViewById(R.id.xel_selectionDialog_rv_list);
        ImageButton xel_selectionDialog_ibtn_close = (ImageButton) innerView.findViewById(R.id.xel_selectionDialog_ibtn_close);

        xelSelectionDialogAdapter = new XELSelectionDialogAdapter((ArrayList)_dataList, selectedPosition);
        xelSelectionDialogAdapter.setItemClick(new XELSelectionDialogAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {

                XELHandlerUtil.PostDelayed(200, new XELHandlerUtil.DelayedCompleteCallback() {
                    @Override
                    public void DelayComplete() {
                        mItemSelectListener.onItemSelect(position, requestId);
                        mDialog.dismiss();
                    }
                });

            }
        });

        xel_selectionDialog_rv_list.setLayoutManager(new LinearLayoutManager(cxt));
        xel_selectionDialog_rv_list.setAdapter(xelSelectionDialogAdapter);

//        xel_selectionDialog_rv_list.setSelection(selectedPosition);


        xel_selectionDialog_ibtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });


        // Divider
        Drawable divider = cxt.getResources().getDrawable(R.drawable.xel_recyclerview_divider);
        int inset = cxt.getResources().getDimensionPixelSize(R.dimen.xel_divider_margin);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);

        DividerItemDecorator itemDecoration = new DividerItemDecorator(insetDivider);
        xel_selectionDialog_rv_list.addItemDecoration(itemDecoration);


        // Dialog 위치 이동 시키기


//        int width = (int)(cxt.getResources().getDisplayMetrics().widthPixels);
//        int height = (int)(cxt.getResources().getDisplayMetrics().heightPixels);
//
//        mDialog.getWindow().setLayout(width, height);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;//previously I used MATCH_PARENT here
        mDialog.getWindow().setLayout(width, height);

        mDialog.getWindow().setWindowAnimations(R.style.XEL_AnimationPopupStyle_Bottom);

        mDialog.getWindow().setGravity(Gravity.BOTTOM);

        mDialog.show();
    }

    /**
     * 리사이클러 뷰 디바이더 - 마지막 아이템 아래건은 디바이더 안보이게 한다.
     */
    public class DividerItemDecorator extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public DividerItemDecorator(Drawable divider) {
            mDivider = divider;
        }

        @Override
        public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i <= childCount - 2; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
    }
}
