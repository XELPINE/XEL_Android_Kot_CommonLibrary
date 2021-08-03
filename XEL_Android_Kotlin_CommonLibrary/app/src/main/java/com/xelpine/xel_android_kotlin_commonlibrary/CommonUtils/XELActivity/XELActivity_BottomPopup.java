package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter.XELBottomPopupListAdapter;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface;
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil;
import com.xelpine.xel_android_kotlin_commonlibrary.R;

import java.util.ArrayList;

/**
 * 바닥에서 떠오르는 형태의 바텀 팝업.
 * 최대 높이가 레이아웃에 설정되어 있으며, 최대 높이 이하는 리스트 높이만큼만 떠오르게 된다.
 * 제네릭을 사용하여 XELCommonSelectionInterface 값을 기준으로 가져오게 된다.
 */
public class XELActivity_BottomPopup extends XELActivity_Base {

    // TAG
    public static final String BOTTOMPOPUP_VIEW_TITLE = "BOTTOMPOPUP_VIEW_TITLE";   // 타이틀
    public static final String BOTTOMPOPUP_VIEW_LIST = "BOTTOMPOPUP_VIEW_LIST";   // 팝업에 띄울 리스트

    public static final String BOTTOMPOPUP_RESULT_TAG = "BOTTOMPOPUP_RESULT_TAG";   // 결과 데이터 태그
    public static final String BOTTOMPOPUP_RESULT_POSITION = "BOTTOMPOPUP_RESULT_POSITION";   // 선택한 위치


    // Components
    ConstraintLayout xel_activity_bottomPopup_cl_mainLayout;
    TextView xel_activity_bottomPopup_tv_title;
    ImageButton xel_activity_bottomPopup_ibtn_close;
    RecyclerView xel_activity_bottomPopup_rv_list;

    // Adapter
    private XELBottomPopupListAdapter xelBottomPopupListAdapter;

    // Data
    private int resultTag;
    private String title;
    ArrayList<? extends XELCommonSelectionInterface> arrayList_data;

    @Override
    protected void beforeCreate() {

        super.beforeCreate();


    }

    @Override
    protected void doCreate(Bundle savedInstanceState) {
        super.doCreate(savedInstanceState);
        setContentView(R.layout.xel_activity_bottompopup);

    }

    @Override
    protected PresetAnimation setPresetAnimation() {
        return PresetAnimation.SLIDE_BOTTOM;
    }

    @Override
    protected void setWindowTransitions() {

    }

    @Override
    protected NFCReadMode setNFCReadMode() {
        return NFCReadMode.NONE;
    }

    @Override
    protected void initLayout() {

        /**
         * findViewById
         */
        xel_activity_bottomPopup_cl_mainLayout = (ConstraintLayout) findViewById(R.id.xel_activity_bottomPopup_cl_mainLayout);
        xel_activity_bottomPopup_tv_title = (TextView) findViewById(R.id.xel_activity_bottomPopup_tv_title);
        xel_activity_bottomPopup_ibtn_close = (ImageButton) findViewById(R.id.xel_activity_bottomPopup_ibtn_close);
        xel_activity_bottomPopup_rv_list = (RecyclerView) findViewById(R.id.xel_activity_bottomPopup_rv_list);

        /**
         * setListener
         */
        xel_activity_bottomPopup_cl_mainLayout.setOnClickListener(onClickListener_main);
        xel_activity_bottomPopup_ibtn_close.setOnClickListener(onClickListener_main);
    }

    @Override
    protected void initData() {

        /**
         * getIntent
         */
        resultTag = getIntent().getIntExtra(BOTTOMPOPUP_RESULT_TAG, -1);
        title = getIntent().getStringExtra(BOTTOMPOPUP_VIEW_TITLE);
        arrayList_data = (ArrayList<? extends XELCommonSelectionInterface>) getIntent().getSerializableExtra(BOTTOMPOPUP_VIEW_LIST);

        xelBottomPopupListAdapter = new XELBottomPopupListAdapter(arrayList_data);
        xelBottomPopupListAdapter.setItemClick(onItemClick_main);
        xel_activity_bottomPopup_rv_list.setLayoutManager(new LinearLayoutManager(this));
        xel_activity_bottomPopup_rv_list.setAdapter(xelBottomPopupListAdapter);

        /**
         * setData
         */
        xel_activity_bottomPopup_tv_title.setText(title);

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
        Drawable divider = getResources().getDrawable(R.drawable.xel_recyclerview_divider);
        int inset = getResources().getDimensionPixelSize(R.dimen.xel_divider_margin);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);

        DividerItemDecorator itemDecoration = new DividerItemDecorator(insetDivider);
        xel_activity_bottomPopup_rv_list.addItemDecoration(itemDecoration);
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

    View.OnClickListener onClickListener_main = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.xel_activity_bottomPopup_cl_mainLayout:
                case R.id.xel_activity_bottomPopup_ibtn_close:

                    finish();

                    break;
            }

        }
    };

    /**
     * 리사이클러뷰 아이템 클릭
     */
    XELBottomPopupListAdapter.ItemClick onItemClick_main = new XELBottomPopupListAdapter.ItemClick() {
        @Override
        public void onClick(View view, int position) {

            XELHandlerUtil.PostDelayed(300, new XELHandlerUtil.DelayedCompleteCallback() {
                @Override
                public void DelayComplete() {
                    // 선택 시 결과 전달
                    Intent result = new Intent();
                    result.putExtra(BOTTOMPOPUP_RESULT_TAG, resultTag);
                    result.putExtra(BOTTOMPOPUP_RESULT_POSITION, position);

                    setResult(RESULT_OK, result);

                    finish();
                }
            });



            switch (position)
            {
//                case 0:
//                    Intent intent_startAssetDataLoading = new Intent(XELBottomPopup.this, Activity_AssetDataLoading.class);
//                    startActivity(intent_startAssetDataLoading);
//                    break;
//
//                case 1:
//                    Intent intent_nfcWrite = new Intent(XELBottomPopup.this, Activity_NFCWrite.class);
//                    startActivity(intent_nfcWrite);
//                    break;
//
//                case 2:
//                    Intent intent_assetStatusLookup = new Intent(XELBottomPopup.this, Activity_AssetStatusLookupHeader.class);
//                    startActivity(intent_assetStatusLookup);
//                    break;
//
//                case 3:
//                    Intent intent_assetDueDiligence = new Intent(XELBottomPopup.this, Activity_AssetDueDiligenceHeader.class);
//                    startActivity(intent_assetDueDiligence);
//                    break;
//
//                case 4:
//                    Intent intent_dueDiligenceResult = new Intent(XELBottomPopup.this, Activity_DueDiligenceResultHeader.class);
//                    startActivity(intent_dueDiligenceResult);
//                    break;
            }

        }
    };

    @Override
    protected void DisplayLandscapeAfter() {

    }

    @Override
    protected void DisplayPortraitAfter() {

    }

    @Override
    protected void initAfterLogic() {

    }

    @Override
    protected void doPause() {

    }

    @Override
    protected void doResume() {

    }

    @Override
    protected void doDestroy() {

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Resources r = Resources.getSystem();
        Configuration config = r.getConfiguration();
        if( config.orientation == ORIENTATION_LANDSCAPE )
        {
            // 네비바에 색상을 강제 지정.
            getWindow().setNavigationBarColor(getResources().getColor(R.color.common_transparent));
        }
        else
        {
            // 네비바에 색상을 강제 지정.
            getWindow().setNavigationBarColor(getResources().getColor(R.color.common_white_nightmode));
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        switch(newConfig.orientation){

            case ORIENTATION_LANDSCAPE:

                // 네비바에 색상을 강제 지정.
                getWindow().setNavigationBarColor(getResources().getColor(R.color.common_transparent));

                break;

            case Configuration.ORIENTATION_PORTRAIT:

                // 네비바에 색상을 강제 지정.
                getWindow().setNavigationBarColor(getResources().getColor(R.color.common_white_nightmode));

                break;

        }
    }
}
