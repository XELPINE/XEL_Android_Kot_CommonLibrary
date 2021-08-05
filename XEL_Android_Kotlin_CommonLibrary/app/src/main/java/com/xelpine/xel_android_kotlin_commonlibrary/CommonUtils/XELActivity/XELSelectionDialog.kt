package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity

import android.app.Activity
import android.app.Dialog
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter.XELSelectionDialogAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.ArrayList

/**
 * 선택 다이얼로그.
 * 하지만 StatusBar가 검은색이 되는 문제가 남는다.
 */
class XELSelectionDialog(private val cxt: Activity) {
    private lateinit var xelSelectionDialogAdapter: XELSelectionDialogAdapter
    private var mItemSelectListener: onItemSelectListener? = null

    interface onItemSelectListener {
        fun onItemSelect(position: Int, requestId: Int)
    }

    fun showDialog(
        requestId: Int,
        _dataList: ArrayList<out XELCommonSelectionInterface>,
        listener: onItemSelectListener?
    ) {
        showDialog(requestId, _dataList, listener, -1)
    }

    //    public void showDialog(final int requestId, final Object _dataList, onItemSelectListener listener, int selectedPosition) {
    //        showDialog(requestId, _dataList, listener, -1);
    //    }
    // 선택창
    fun showDialog(
        requestId: Int,
        _dataList: ArrayList<out XELCommonSelectionInterface>,
        listener: onItemSelectListener?,
        selectedPosition: Int
    ) {
        val innerView: View = cxt.layoutInflater.inflate(R.layout.xel_selectondialog, null)
        mItemSelectListener = listener

//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            ((View)innerView.findViewById(R.id.empty_view)).setVisibility(View.GONE);
//        }
        val mDialog = Dialog(cxt)
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialog.setContentView(innerView)
        val xel_selectionDialog_rv_list =
            innerView.findViewById<View>(R.id.xel_selectionDialog_rv_list) as RecyclerView
        val xel_selectionDialog_ibtn_close =
            innerView.findViewById<View>(R.id.xel_selectionDialog_ibtn_close) as ImageButton
        xelSelectionDialogAdapter =
            XELSelectionDialogAdapter(_dataList, selectedPosition)
        xelSelectionDialogAdapter.setItemClick(object : XELSelectionDialogAdapter.ItemClick {
            override fun onClick(view: View?, position: Int) {
                XELHandlerUtil.PostDelayed(200, object : XELHandlerUtil.DelayedCompleteCallback {
                    override fun DelayComplete() {
                        mItemSelectListener!!.onItemSelect(position, requestId)
                        mDialog.dismiss()
                    }
                })
            }
        })
        xel_selectionDialog_rv_list.layoutManager = LinearLayoutManager(cxt)
        xel_selectionDialog_rv_list.adapter = xelSelectionDialogAdapter

//        xel_selectionDialog_rv_list.setSelection(selectedPosition);
        xel_selectionDialog_ibtn_close.setOnClickListener { mDialog.dismiss() }


        // Divider
        val divider = cxt.resources.getDrawable(R.drawable.xel_recyclerview_divider)
        val inset = cxt.resources.getDimensionPixelSize(R.dimen.xel_divider_margin)
        val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
        val itemDecoration: DividerItemDecorator = DividerItemDecorator(insetDivider)
        xel_selectionDialog_rv_list.addItemDecoration(itemDecoration)


        // Dialog 위치 이동 시키기


//        int width = (int)(cxt.getResources().getDisplayMetrics().widthPixels);
//        int height = (int)(cxt.getResources().getDisplayMetrics().heightPixels);
//
//        mDialog.getWindow().setLayout(width, height);
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT //previously I used MATCH_PARENT here
        mDialog.window!!.setLayout(width, height)
        mDialog.window!!.setWindowAnimations(R.style.XEL_AnimationPopupStyle_Bottom)
        mDialog.window!!.setGravity(Gravity.BOTTOM)
        mDialog.show()
    }

    /**
     * 리사이클러 뷰 디바이더 - 마지막 아이템 아래건은 디바이더 안보이게 한다.
     */
    inner class DividerItemDecorator(private val mDivider: Drawable) :
        ItemDecoration() {
        override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val dividerLeft = parent.paddingLeft
            val dividerRight = parent.width - parent.paddingRight
            val childCount = parent.childCount
            for (i in 0..childCount - 2) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val dividerTop = child.bottom + params.bottomMargin
                val dividerBottom = dividerTop + mDivider.intrinsicHeight
                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
                mDivider.draw(canvas)
            }
        }
    }
}
