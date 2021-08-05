package com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.Interface.XELCommonSelectionInterface
import com.xelpine.xel_android_kotlin_commonlibrary.R
import java.util.ArrayList

class XELBottomPopupListAdapter(
    contactList: ArrayList<out XELCommonSelectionInterface>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    // Data
    private val launchList: ArrayList<out XELCommonSelectionInterface>

    //아이템 클릭시 실행 함수
    private var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View?, position: Int)
    }

    //아이템 클릭시 실행 함수 등록 함수
    fun setItemClick(itemClick: ItemClick?) {
        this.itemClick = itemClick
    }

    override fun getItemCount(): Int {
        return launchList.size
    }

    override fun onBindViewHolder(contactViewHolder: RecyclerView.ViewHolder, position: Int) {
//        XELLogUtil.i(XELGlobalDefine.TAG, "===== LaunchAdapter onBindViewHolder position = " + position);
        val model: XELCommonSelectionInterface = launchList[position]

        when (contactViewHolder!!.itemViewType)
        {
            DEFAULT_VIEWTYPE -> {
                val viewHolder_default = contactViewHolder as MainMenuDtoViewHolder?
                viewHolder_default!!.xel_row_bottomPopup_tv_title.setText(
                    model.Name().toString() + " (" + model.Code() + ")"
                )

                // onClick
                viewHolder_default.xel_row_bottomPopup_cl_mainLayout.setOnClickListener { view ->
                    if (itemClick != null) {
                        itemClick!!.onClick(view, position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView: View? = null
        when (viewType) {
            DEFAULT_VIEWTYPE -> {
                itemView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.xel_row_bottompopup, viewGroup, false)
                return MainMenuDtoViewHolder(itemView)
            }

            else -> {

                itemView = LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.xel_row_bottompopup, viewGroup, false)
                return MainMenuDtoViewHolder(itemView)
            }
        }


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

    override fun getItemViewType(position: Int): Int {
//        return launchList.get(position).getMessageType();
        return DEFAULT_VIEWTYPE
    }

    inner class MainMenuDtoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var xel_row_bottomPopup_cl_mainLayout: ConstraintLayout
        var xel_row_bottomPopup_tv_title: TextView

        init {
            xel_row_bottomPopup_cl_mainLayout =
                v.findViewById<View>(R.id.xel_row_bottomPopup_cl_mainLayout) as ConstraintLayout
            xel_row_bottomPopup_tv_title =
                v.findViewById<View>(R.id.xel_row_bottomPopup_tv_title) as TextView
        }
    }

    companion object {
        // DEFINE
        private const val DEFAULT_VIEWTYPE = 1
    }

    init {
        launchList = contactList
    }
}