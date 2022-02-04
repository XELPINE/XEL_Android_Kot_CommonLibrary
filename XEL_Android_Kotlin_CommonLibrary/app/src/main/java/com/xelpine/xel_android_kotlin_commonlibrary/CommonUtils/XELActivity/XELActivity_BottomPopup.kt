package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter.XELBottomPopupListAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.ArrayList

/**
 * 바닥에서 떠오르는 형태의 바텀 팝업.
 * 최대 높이가 레이아웃에 설정되어 있으며, 최대 높이 이하는 리스트 높이만큼만 떠오르게 된다.
 * 제네릭을 사용하여 XELCommonSelectionInterface 값을 기준으로 가져오게 된다.
 */
class XELActivity_BottomPopup : XELActivity_Base()
{
    companion object
    {
        // TAG
        // 입력
        const val BOTTOMPOPUP_RESULT_TAG = "BOTTOMPOPUP_RESULT_TAG" // 결과 데이터 태그
        const val BOTTOMPOPUP_VIEW_TITLE = "BOTTOMPOPUP_VIEW_TITLE" // 타이틀
        const val BOTTOMPOPUP_VIEW_LIST = "BOTTOMPOPUP_VIEW_LIST" // 팝업에 띄울 리스트

        // 출력
        const val BOTTOMPOPUP_RESULT_POSITION = "BOTTOMPOPUP_RESULT_POSITION" // 선택한 위치
    }

    // Components
    var xel_activity_bottomPopup_cl_mainLayout: ConstraintLayout? = null
    var xel_activity_bottomPopup_tv_title: TextView? = null
    var xel_activity_bottomPopup_ibtn_close: ImageButton? = null
    var xel_activity_bottomPopup_rv_list: RecyclerView? = null

    // Adapter
    lateinit var xelBottomPopupListAdapter: XELBottomPopupListAdapter

    // Data
    private var resultTag = 0
    private var title: String? = null
    lateinit var arrayList_data: ArrayList<out XELCommonSelectionInterface?>

    protected override fun beforeCreate() {
        super.beforeCreate()
    }

    protected override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.xel_activity_bottompopup)
    }

    protected override fun setPresetAnimation(): PresetAnimation {
        return PresetAnimation.SLIDE_BOTTOM
    }

    protected override fun setWindowTransitions() {}
    protected override fun setNFCReadMode(): NFCReadMode {
        return NFCReadMode.NONE
    }

    protected override fun initLayout() {
        /**
         * findViewById
         */
        xel_activity_bottomPopup_cl_mainLayout =
            findViewById(R.id.xel_activity_bottomPopup_cl_mainLayout) as ConstraintLayout?
        xel_activity_bottomPopup_tv_title =
            findViewById(R.id.xel_activity_bottomPopup_tv_title) as TextView?
        xel_activity_bottomPopup_ibtn_close =
            findViewById(R.id.xel_activity_bottomPopup_ibtn_close) as ImageButton?
        xel_activity_bottomPopup_rv_list =
            findViewById(R.id.xel_activity_bottomPopup_rv_list) as RecyclerView?
        /**
         * setListener
         */
        xel_activity_bottomPopup_cl_mainLayout!!.setOnClickListener(onClickListener_main)
        xel_activity_bottomPopup_ibtn_close!!.setOnClickListener(onClickListener_main)
    }

    protected override fun initData() {
        /**
         * getIntent
         */
        resultTag = getIntent().getIntExtra(BOTTOMPOPUP_RESULT_TAG, -1)
        title = getIntent().getStringExtra(BOTTOMPOPUP_VIEW_TITLE)
        arrayList_data = getIntent().getSerializableExtra(BOTTOMPOPUP_VIEW_LIST) as ArrayList<out XELCommonSelectionInterface?>
        xelBottomPopupListAdapter = XELBottomPopupListAdapter(arrayList_data)
        xelBottomPopupListAdapter!!.setItemClick(onItemClick_main)
        xel_activity_bottomPopup_rv_list!!.layoutManager = LinearLayoutManager(this)
        xel_activity_bottomPopup_rv_list!!.adapter = xelBottomPopupListAdapter
        /**
         * setData
         */
        xel_activity_bottomPopup_tv_title!!.text = title

        // Divider
//        xel_activity_bottomPopup_rv_list.addItemDecoration(new DividerItemDecoration(XELBottomPopup.this, LinearLayoutManager.VERTICAL));

//        // Divider
//        int[] ATTRS = new int[]{android.R.attr.listDivider};
//
//        TypedArray a = obtainStyledAttributes(ATTRS);
//        Drawable divider = getResources().getDrawable(R.drawable.xel_recyclerview_divider);
//        int inset = getResources().getDimensionPixelSize(R.dimen.xel_divider_margin);
//        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);
//        a.recycle();
//
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
//        itemDecoration.setDrawable(insetDivider);
//        xel_activity_bottomPopup_rv_list.addItemDecoration(itemDecoration);

        // Divider
        val divider: Drawable = getResources().getDrawable(R.drawable.xel_recyclerview_divider)
        val inset: Int = getResources().getDimensionPixelSize(R.dimen.xel_divider_margin)
        val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)
        val itemDecoration: DividerItemDecorator = DividerItemDecorator(insetDivider)
        xel_activity_bottomPopup_rv_list!!.addItemDecoration(itemDecoration)
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

    var onClickListener_main =
        View.OnClickListener { v ->
            when (v.id) {
                R.id.xel_activity_bottomPopup_cl_mainLayout, R.id.xel_activity_bottomPopup_ibtn_close -> finish()
            }
        }

    /**
     * 리사이클러뷰 아이템 클릭
     */
    var onItemClick_main: XELBottomPopupListAdapter.ItemClick = object : XELBottomPopupListAdapter.ItemClick
    {
        override fun onClick(view: View?, position: Int) {
            XELHandlerUtil.PostDelayed(300, object : XELHandlerUtil.DelayedCompleteCallback {
                override fun DelayComplete() {
                    // 선택 시 결과 전달
                    val result = Intent()
                    result.putExtra(BOTTOMPOPUP_RESULT_TAG, resultTag)
                    result.putExtra(BOTTOMPOPUP_RESULT_POSITION, position)
                    setResult(RESULT_OK, result)
                    finish()
                }
            })
            when (position) {
            }
        }
    }

    protected override fun displayLandscapeAfter() {}

    protected override fun displayPortraitAfter() {}

    protected override fun initAfterLogic() {}

    protected override fun doPause() {}

    protected override fun doResume() {}

    override fun doStart() {
    }

    override fun doStop() {
    }

    protected override fun doDestroy() {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val r = Resources.getSystem()
        val config = r.configuration
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // 네비바에 색상을 강제 지정.
            getWindow().setNavigationBarColor(getResources().getColor(R.color.common_transparent))
        } else {
            // 네비바에 색상을 강제 지정.
            getWindow().setNavigationBarColor(getResources().getColor(R.color.common_white_nightmode))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE ->
                // 네비바에 색상을 강제 지정.
                getWindow().setNavigationBarColor(getResources().getColor(R.color.common_transparent))
            Configuration.ORIENTATION_PORTRAIT ->
                // 네비바에 색상을 강제 지정.
                getWindow().setNavigationBarColor(getResources().getColor(R.color.common_white_nightmode))
        }
    }


}
