package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELExtension.notifyObserver
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELHandlerUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELSystemUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityRoomBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.callback.CommonDataLoadCallback
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.appdatabase.GSCharactersAppDatabase
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSCharacters
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.RoomInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Room
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Room

class Activity_Room : XELActivity_Base(), RoomInterface
{
    // DataBinding
    private lateinit var mBinding: ActivityRoomBinding
    // ViewModel
    private val mViewModel: ViewModel_Room by viewModels{ ViewModelFactory_Room(application, this, this, this) }

    // Adapter
//    lateinit var roomListAdapter : RoomListAdapter

    // Room DB
    var gsCharactersDB : GSCharactersAppDatabase? = null
    var gsCharactersList = mutableListOf<GSCharacters>()


    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_room)

        mBinding.lifecycleOwner = this
        mBinding.roomViewModel = mViewModel
    }

    override fun setPresetAnimation(): PresetAnimation? {
        return PresetAnimation.SLIDE_RIGHT
    }

    override fun setWindowTransitions() {

    }

    override fun setNFCReadMode(): NFCReadMode? {
        return NFCReadMode.NONE
    }

    override fun initLayout() {
        /**
         * 기본세팅
         */
        setSupportActionBar(mBinding.toolbar)

        // 타이틀 세팅
        mBinding.toolbar.title = "Room Sample"

        // 뒤로가기 버튼
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
//        roomListAdapter = RoomListAdapter(this, mViewModel.RoomList)
////        RoomListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
//        mBinding.rvList.adapter = RoomListAdapter
//        mBinding.rvList.layoutManager = LinearLayoutManager(this)
//
//        // 리스트 변화 감시
//        mViewModel.RoomList.observe(this, Observer {
//
//            XELLogUtil.e_function(XELGlobalDefine.TAG, "리스트 변화 감시 : " + mViewModel.RoomList.value?.size)
//
//
//
//            mBinding.rvList.adapter?.notifyDataSetChanged()
//        })

        gsCharactersDB = GSCharactersAppDatabase.getInstance(this)
    }

    override fun displayLandscapeAfter() {


    }

    override fun displayPortraitAfter() {

    }

    override fun initAfterLogic() {

        mBinding.button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM INSERT (ROOM 저장)")

                gsCharactersDB!!.gsCharactersDao()
                    .insertAll(GSCharacters("ID2", "NAME2", "NAMEENG2", "SKILL2", "PROP2"))

                XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM SELECT (ROOM 조회) : " + gsCharactersDB!!.gsCharactersDao().getAll().toString())

            }
        })



    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}