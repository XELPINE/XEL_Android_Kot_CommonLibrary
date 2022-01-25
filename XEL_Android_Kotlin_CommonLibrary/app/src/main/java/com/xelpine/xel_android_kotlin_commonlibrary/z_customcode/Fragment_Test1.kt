package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityFragmentBinding
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.FragmentTest1Binding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.adapter.Frag1ListAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.callback.CommonDataLoadCallback
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.Frag1Interface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Frag1
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Fragment
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Frag1
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Fragment


class Fragment_Test1 : Fragment(), Frag1Interface
{
    // DataBinding
    private lateinit var mBinding: FragmentTest1Binding
    // ViewModel
    private val mViewModel: ViewModel_Frag1 by viewModels{ ViewModelFactory_Frag1(requireActivity().application,
        requireContext(), requireActivity() as AppCompatActivity, this) }

    // Adapter
    lateinit var frag1ListAdapter : Frag1ListAdapter
    
    
    

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 데이터 바인딩
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1, container, false)

//        mBinding.lifecycleOwner = this
//        mBinding.framgentTest1ViewModel = mViewModel




        //fragment_two 가져오기
//        val view: View = inflater.inflate(R.layout.fragment_test1, null)

//        return super.onCreateView(inflater, container, savedInstanceState)
        
        mViewModel.CommonDataInfoInit(object : CommonDataLoadCallback{
            override fun CommonDataLoadSuccess() {
            }

            override fun CommonDataLoadFailed() {
            }
        })

        frag1ListAdapter = Frag1ListAdapter(requireContext(), mViewModel.frag1List)
//        frag1ListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
        mBinding.rvList.adapter = frag1ListAdapter
        mBinding.rvList.layoutManager = LinearLayoutManager(requireContext())

        // 리스트 변화 감시
        mViewModel.frag1List.observe(requireActivity(), Observer {

            XELLogUtil.e_function(XELGlobalDefine.TAG, "리스트 변화 감시 : " + mViewModel.frag1List.value?.size)



            mBinding.rvList.adapter?.notifyDataSetChanged()
        })

        return mBinding.root
    }
}