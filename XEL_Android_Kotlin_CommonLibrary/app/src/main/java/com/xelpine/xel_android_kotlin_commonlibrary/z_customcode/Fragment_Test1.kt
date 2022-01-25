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
import com.android.volley.Request
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalApplication
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELFragment_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELVolleyUtils.XELVolleyUtil
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
import java.lang.Exception
import java.util.HashMap


class Fragment_Test1 : XELFragment_Base(), Frag1Interface
{
    // DataBinding
    private lateinit var mBinding: FragmentTest1Binding
    // ViewModel
    private val mViewModel: ViewModel_Frag1 by viewModels{ ViewModelFactory_Frag1(requireActivity().application,
        requireContext(), requireActivity() as AppCompatActivity, this) }

    // Adapter
    lateinit var frag1ListAdapter : Frag1ListAdapter


    override fun doCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 데이터 바인딩
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_test1, container, false)



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

    override fun initLayout() {
    }

    override fun initData() {
    }

    override fun displayLandscapeAfter() {

    }

    override fun displayPortraitAfter() {

    }

    override fun initAfterLogic() {

        // URL
        val int_method = Request.Method.GET
        val url1 =
            "http://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey=lphi2j7DdXEt39plDjx6Hazo%2BLN4NNqHft%2FVrOSFdy2uDUQunzCH7EZehMQaUPqnDCR3m5jV1yvWToknAp4L9A%3D%3D&mapX=127.02955&mapY=37.492335&radius=1000&pageNo=1&numOfRows=10&listYN=Y&arrange=A&MobileOS=AND&MobileApp=com.example.testvolley"
        val url2 =
            "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=perl&site=stackoverflow"

        // GET
        // GET일 때는 hashmap 데이터를 null로 넘긴다.
        XELVolleyUtil.startStringRequestData(
            requireContext(),
            Request.Method.GET,
            url1,
            null,
            "TAG_1",
            "UTF-8",
            XELGlobalApplication.getInstance().VolleyDefaultRetryPolicy(),
            this
        )
        val map = HashMap<String, String>()
        map["testKey1"] = "testValue1"
        map["testKey2"] = "testValue2"

        // POST
        // POST는 항상 hashmap으로 전달할 데이터를 넘긴다.
        XELVolleyUtil.startStringRequestData(
            requireContext(),
            Request.Method.GET,
            url2,
            map,
            "TAG_2",
            "UTF-8",
            XELGlobalApplication.getInstance().VolleyDefaultRetryPolicy(),
            this
        )

    }

    override fun onDataResponseSucess(tag: String, data: String) {
        super.onDataResponseSucess(tag, data)

        XELDialogUtil.closeWait(0)
        XELLogUtil.i_network(XELGlobalDefine.TAG, "TEST onDataResponseSucess")
        XELLogUtil.i_network(XELGlobalDefine.TAG, "TAG = $tag")
        XELLogUtil.i_network(XELGlobalDefine.TAG, "DATA = $data")
    }

    override fun onDataResponseError(tag: String, errorCode: Int) {
        super.onDataResponseError(tag, errorCode)

        XELDialogUtil.closeWait(0)
        XELLogUtil.e_network(XELGlobalDefine.TAG, "onDataResponseError")
        XELLogUtil.e_network(XELGlobalDefine.TAG, "errorhandler TAG = $tag")
        XELLogUtil.e_network(
            XELGlobalDefine.TAG,
            "ERROR_CODE = $errorCode"
        )
    }

    override fun onDataException(e: Exception) {
        super.onDataException(e)
    }
}