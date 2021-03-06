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
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.db_room.entity.GSFarmingResources
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

        // ????????? ?????????
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_room)

        mBinding.lifecycleOwner = this
        mBinding.roomViewModel = mViewModel
    }

    override fun setTheme() {
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
         * ????????????
         */
        setSupportActionBar(mBinding.toolbar)

        // ????????? ??????
        mBinding.toolbar.title = "Room Sample"

        // ???????????? ??????
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
//        roomListAdapter = RoomListAdapter(this, mViewModel.RoomList)
////        RoomListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
//        mBinding.rvList.adapter = RoomListAdapter
//        mBinding.rvList.layoutManager = LinearLayoutManager(this)
//
//        // ????????? ?????? ??????
//        mViewModel.RoomList.observe(this, Observer {
//
//            XELLogUtil.e_function(XELGlobalDefine.TAG, "????????? ?????? ?????? : " + mViewModel.RoomList.value?.size)
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

                XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM INSERT (ROOM ??????)")

//                gsCharactersDB!!.gsCharactersDao()
//                    .insertAll(GSCharacters("ID2", "NAME2", "NAMEENG2", "SKILL2", "PROP2"))

                gsCharactersDB!!.gsFarmingResourcesDao().insertAll(GSFarmingResources("ID1", "FARMKOR1",
                    "FARMENG1", "COUNTRYID1", "URL1", 3000, 4000))

                XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM SELECT (ROOM ?????? gsCharactersDao) : " + gsCharactersDB!!.gsCharactersDao().getAll().toString())
                XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM SELECT (ROOM ?????? gsFarmingResourcesDao) : " + gsCharactersDB!!.gsFarmingResourcesDao().getAll().toString())

            }
        })

        gsCharactersDB!!.gsFarmingResourcesDao()



        XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM SELECT (ROOM ?????? gsCharactersDao) : " + gsCharactersDB!!.gsCharactersDao().getAll().toString())
        XELLogUtil.i_function(XELGlobalDefine.TAG, "BTN1 ROOM SELECT (ROOM ?????? gsFarmingResourcesDao) : " + gsCharactersDB!!.gsFarmingResourcesDao().getAll().toString())

    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doStart() {
    }

    override fun doStop() {
    }

    override fun doDestroy() {
    }
}