package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface;
import com.xelpine.xel_android_kotlin_commonlibrary.R;

import java.util.ArrayList;


public class XELBottomPopupListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    // DEFINE
    private static final int DEFAULT_VIEWTYPE = 1;

    // Data
    private ArrayList<? extends XELCommonSelectionInterface> launchList;







    //아이템 클릭시 실행 함수
    private ItemClick itemClick;
    public interface ItemClick {
        public void onClick(View view, int position);
    }

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public XELBottomPopupListAdapter(ArrayList<? extends XELCommonSelectionInterface> contactList)
    {
        launchList = contactList;
    }

    @Override
    public int getItemCount() {
        return launchList.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder contactViewHolder, final int position)
    {
//        XELLogUtil.i(XELGlobalDefine.TAG, "===== LaunchAdapter onBindViewHolder position = " + position);

        XELCommonSelectionInterface model = launchList.get(position);

        switch (contactViewHolder.getItemViewType())
        {
            case DEFAULT_VIEWTYPE:
                MainMenuDtoViewHolder viewHolder_default = (MainMenuDtoViewHolder) contactViewHolder;

                viewHolder_default.xel_row_bottomPopup_tv_title.setText(model.getName() + " (" + model.getCode() + ")");

                // onClick
                viewHolder_default.xel_row_bottomPopup_cl_mainLayout.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (itemClick != null)
                        {
                            itemClick.onClick(view, position);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = null;

//        XELLogUtil.i(XELGlobalDefine.TAG, "===== LaunchAdapter onCreateViewHolder getMessageType : " + viewType);

        switch (viewType)
        {
            case DEFAULT_VIEWTYPE:
                itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xel_row_bottompopup, viewGroup, false);

                return new MainMenuDtoViewHolder (itemView);
        }

        return null;



        // 클릭 리스너
//        itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v)
//            {
//                int itemPosition = recyclerView_favoriteLst.getChildPosition(v);
//                String item = String.valueOf(al.get(itemPosition).favorite_price);
//                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemViewType(int position)
    {
//        return launchList.get(position).getMessageType();
        return DEFAULT_VIEWTYPE;
    }

    public class MainMenuDtoViewHolder extends RecyclerView.ViewHolder
    {
        ConstraintLayout xel_row_bottomPopup_cl_mainLayout;
        TextView xel_row_bottomPopup_tv_title;

        public MainMenuDtoViewHolder (View v)
        {
            super(v);

            xel_row_bottomPopup_cl_mainLayout = (ConstraintLayout) v.findViewById(R.id.xel_row_bottomPopup_cl_mainLayout);
            xel_row_bottomPopup_tv_title = (TextView) v.findViewById(R.id.xel_row_bottomPopup_tv_title);
        }
    }
};