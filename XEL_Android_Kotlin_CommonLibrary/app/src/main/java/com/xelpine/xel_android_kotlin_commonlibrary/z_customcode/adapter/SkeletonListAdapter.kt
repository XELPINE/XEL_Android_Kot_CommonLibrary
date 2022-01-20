package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.RowActivitySkeletonBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.SkeletonSampleDataDto

/**
 * 실사결과 - 라인 리스트 어댑터
 */
class SkeletonListAdapter(
    private var itemList: MutableLiveData<ArrayList<SkeletonSampleDataDto>>) :
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
        val model: SkeletonSampleDataDto = itemList.value!![position]

        when (viewHolder!!.itemViewType)
        {
            DEFAULT_VIEWTYPE ->
            {
                val viewHolder_default = viewHolder as AssetDueDiligenceInfoDtoViewHolder

                viewHolder_default.onBind(itemList.value!![position])

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

                val binding = RowActivitySkeletonBinding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false)

                return AssetDueDiligenceInfoDtoViewHolder(binding)
            }

            else ->
            {
                val binding = RowActivitySkeletonBinding.inflate(
                    LayoutInflater.from(viewGroup.context), viewGroup, false)

                return AssetDueDiligenceInfoDtoViewHolder(binding)
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

    inner class AssetDueDiligenceInfoDtoViewHolder(val mBinding : RowActivitySkeletonBinding) : RecyclerView.ViewHolder(mBinding.root)
    {
        fun onBind(data : SkeletonSampleDataDto)
        {
            mBinding.rowSkeletonSampleDataDtoViewModel = data
        }
    }
}