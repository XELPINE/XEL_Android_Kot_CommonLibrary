package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.RowFragmentFrag1Binding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.Frag1DataDto

/**
 * 실사결과 - 라인 리스트 어댑터
 */
class Frag1ListAdapter(
    private var context: Context,
    private var itemList: MutableLiveData<ArrayList<Frag1DataDto>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    companion object {
        // DEFINE
        private const val DEFAULT_VIEWTYPE = 1
    }

    //아이템 클릭시 실행 함수
    private var itemClick: ItemClick? = null





    interface ItemClick {
        fun onClick(view: View?, position: Int)
    }

    //아이템 클릭시 실행 함수 등록 함수
    fun setItemClick(itemClick: ItemClick?) {
        this.itemClick = itemClick
    }

    // 이미지보기 클릭시 실행 함수
    private var imageViewerButtonClick: ImageViewerButtonClick? = null

    interface ImageViewerButtonClick {
        fun onClick(view: View?, position: Int)
    }

    // 이미지보기 클릭시 실행 함수 등록 함수
    fun setImageViewerButtonClick(imageViewerButtonClick: ImageViewerButtonClick?) {
        this.imageViewerButtonClick = imageViewerButtonClick
    }

    override fun getItemCount(): Int {
        return itemList.value!!.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)
    {
        val model: Frag1DataDto = itemList.value!![position]

        when (viewHolder!!.itemViewType)
        {
            DEFAULT_VIEWTYPE ->
            {
                val viewHolder_default = viewHolder as Frag1DataDtoViewHolder

                viewHolder_default.onBind(itemList.value!![position])

                viewHolder_default.mBinding.ivTitle

                Glide.with(context)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkRdrlN8_R3h9dj8VA_Q5xuoSmX1YgKSAQWw&usqp=CAU")
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(viewHolder_default.mBinding.ivTitle)

//                // 이미지 보기 버튼
//                if (TextUtils.isEmpty(model.imagePath))
//                {
//                    viewHolder_default.mBinding.rowActivityDueDiligenceResultLineBtnImageViewer.visibility =View.GONE
//                }
//                else
//                {
//                    viewHolder_default.mBinding.rowActivityDueDiligenceResultLineBtnImageViewer.visibility =View.VISIBLE
//                }

//                // 실사자가 없으면 실사자 안보이게 한다.
//                if (TextUtils.isEmpty(model.dueDiligenceEmpName))
//                {
//                    viewHolder_default.mBinding.rowActivityDueDiligenceResultLineMcvDueDiligenceResultInfo.visibility =View.GONE
//                }
//                else
//                {
//                    viewHolder_default.mBinding.rowActivityDueDiligenceResultLineMcvDueDiligenceResultInfo.visibility = View.VISIBLE
//                }

//                // onClick
//                viewHolder_default.mBinding.rowActivityDueDiligenceResultLineMcvMainLayout.setOnClickListener { view ->
//                    if (itemClick != null) {
//                        itemClick!!.onClick(view, position)
//                    }
//                }
//
//                // 이미지 버튼 클릭
//                viewHolder_default.mBinding.rowActivityDueDiligenceResultLineBtnImageViewer.setOnClickListener { v ->
//                    if (imageViewerButtonClick != null) {
//                        imageViewerButtonClick!!.onClick(v, position)
//                    }
//                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView: View? = null
        when (viewType) {
            DEFAULT_VIEWTYPE -> {

                val binding = RowFragmentFrag1Binding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false)

                return Frag1DataDtoViewHolder(binding)
            }

            else ->
            {
                val binding = RowFragmentFrag1Binding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false)

                return Frag1DataDtoViewHolder(binding)
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
//        return itemList.get(position).getMessageType();
        return DEFAULT_VIEWTYPE
    }

    inner class Frag1DataDtoViewHolder(val mBinding : RowFragmentFrag1Binding) : RecyclerView.ViewHolder(mBinding.root)
    {
        fun onBind(data : Frag1DataDto)
        {
            mBinding.rowFrag1DtoViewModel = data
        }
    }
}