package com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode.Dto.LaunchDto
import java.util.ArrayList

class LaunchAdapter (var launchList : ArrayList<LaunchDto>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    // DEFINE
    private val DEFAULT_VIEWTYPE = 1

    // Data
//    private var launchList: List<LaunchDto>? = null


    //아이템 클릭시 실행 함수
    private var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View?, position: Int)
    }

    //아이템 클릭시 실행 함수 등록 함수
    fun setItemClick(itemClick: ItemClick?) {
        this.itemClick = itemClick
    }


//    fun LaunchAdapter(contactList: List<LaunchDto>?) {
//        launchList = contactList
//    }

    override fun getItemViewType(position: Int): Int {
//        return launchList.get(position).getMessageType();
        return DEFAULT_VIEWTYPE
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var itemView: View? = null

        XELLogUtil.i(
            XELGlobalDefine.TAG,
            "===== LaunchAdapter onCreateViewHolder getMessageType : " + viewType
        )

        when (viewType) {
            DEFAULT_VIEWTYPE -> {
                itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_launch, viewGroup, false)
                return LaunchModelViewHolder(itemView)
            }

            // 기본값
            else -> {
                itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_launch, viewGroup, false)
                return LaunchModelViewHolder(itemView)
            }
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        XELLogUtil.i(
            XELGlobalDefine.TAG,
            "===== LaunchAdapter onBindViewHolder position = $position"
        )

        val model: LaunchDto = launchList!![position]

        XELLogUtil.i_function(XELGlobalDefine.TAG, "POS [ " + position + "] VIEWTYPE : " + holder.getItemViewType() + " DATA : " + model.menuName)


        when (holder.getItemViewType()) {
            DEFAULT_VIEWTYPE -> {
                val viewHolder_default = holder as LaunchModelViewHolder
                viewHolder_default.tv_menuName.setText(model.menuName)

                // onClick
                viewHolder_default.ll_mainLayout.setOnClickListener { view ->
                    if (itemClick != null) {
                        itemClick!!.onClick(view, position)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return launchList!!.size
    }





    inner class LaunchModelViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var ll_mainLayout: LinearLayout
        var tv_menuName: TextView

        init {
            ll_mainLayout = v.findViewById<View>(R.id.row_launch_ll_mainLayout) as LinearLayout
            tv_menuName = v.findViewById<View>(R.id.row_launch_tv_menuName) as TextView
        }
    }
}