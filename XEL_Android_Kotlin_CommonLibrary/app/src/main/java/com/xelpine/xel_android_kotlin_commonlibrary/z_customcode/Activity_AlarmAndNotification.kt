package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELAlarmUtil
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityAlarmandnotificationBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.AlarmAndNotificationInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_AlarmAndNotification
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_AlarmAndNotification

class Activity_AlarmAndNotification : XELActivity_Base(), AlarmAndNotificationInterface
{
    // DataBinding
    private lateinit var mBinding: ActivityAlarmandnotificationBinding
    // ViewModel
    private val mViewModel: ViewModel_AlarmAndNotification by viewModels{ ViewModelFactory_AlarmAndNotification(application, this, this, this) }

    // Adapter
//    lateinit var AlarmAndNotificationListAdapter : AlarmAndNotificationListAdapter


    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_alarmandnotification)

        mBinding.lifecycleOwner = this
        mBinding.alarmAndNotificationViewModel = mViewModel
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
         * 기본세팅
         */
        setSupportActionBar(mBinding.toolbar)

        // 타이틀 세팅
        mBinding.toolbar.title = "Alarm And Notification"

        // 뒤로가기 버튼
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initData() {
//        AlarmAndNotificationListAdapter = AlarmAndNotificationListAdapter(this, mViewModel.AlarmAndNotificationList)
////        AlarmAndNotificationListAdapter.setImageViewerButtonClick(imageViewerButtonClick)
//        mBinding.rvList.adapter = AlarmAndNotificationListAdapter
//        mBinding.rvList.layoutManager = LinearLayoutManager(this)
//
//        // 리스트 변화 감시
//        mViewModel.AlarmAndNotificationList.observe(this, Observer {
//
//            XELLogUtil.e_function(XELGlobalDefine.TAG, "리스트 변화 감시 : " + mViewModel.AlarmAndNotificationList.value?.size)
//
//
//
//            mBinding.rvList.adapter?.notifyDataSetChanged()
//        })
    }

    override fun displayLandscapeAfter() {


    }

    override fun displayPortraitAfter() {

    }

    override fun initAfterLogic() {

        mBinding.button1.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                XELAlarmUtil.createAlarm(this@Activity_AlarmAndNotification, 1, "Activity Title 1", "Acbtivity Text 1", 5000)
                XELAlarmUtil.createAlarm(this@Activity_AlarmAndNotification, 2, "Activity Title 2", "Acbtivity Text 2", 5000)
                XELAlarmUtil.createAlarm(this@Activity_AlarmAndNotification, 3, "Activity Title 3", "Acbtivity Text 3", 5000)

//                val alarmManager : AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//
//                var alarmIntent = Intent(this@Activity_AlarmAndNotification, XELAlarmReceiver::class.java)
//
//                alarmIntent.putExtra("TITLE", "ACTIVITY TITLE")
//                alarmIntent.putExtra("TEXT", "ACTIVITY TEXT")
//
//                var pendingIntent = PendingIntent.getBroadcast(this@Activity_AlarmAndNotification, 111, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//                /**
//                 * Intent 플래그
//                 *    FLAG_ONE_SHOT : 한번만 사용하고 다음에 이 PendingIntent가 불려지면 Fail을 함
//                 *    FLAG_NO_CREATE : PendingIntent를 생성하지 않음. PendingIntent가 실행중인것을 체크를 함
//                 *    FLAG_CANCEL_CURRENT : 실행중인 PendingIntent가 있다면 기존 인텐트를 취소하고 새로만듬
//                 *    FLAG_UPDATE_CURRENT : 실행중인 PendingIntent가 있다면  Extra Data만 교체함
//                 */
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent); //10초뒤 알람
//                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
//                } else {
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
//
//                }

            }
        })

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