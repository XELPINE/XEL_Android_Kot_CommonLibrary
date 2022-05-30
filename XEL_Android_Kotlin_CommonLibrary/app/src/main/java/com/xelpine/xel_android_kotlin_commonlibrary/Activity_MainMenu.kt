package com.xelpine.xel_android_kotlin_commonlibrary

import android.app.Activity
import android.app.ActivityOptions
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELActivity.XELActivity_BottomPopup
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDateUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELSystemUtil
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.*
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.adapter.LaunchAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.LaunchDto
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.dto.PopupTestDto
import java.util.ArrayList
import androidx.core.app.ActivityCompat

import androidx.core.app.ActivityOptionsCompat




class Activity_MainMenu : XELActivity_Base() {

    // Components
    var rv_main: RecyclerView? = null
    var toolbar: Toolbar? = null

    // adapter
    var launchAdapter: LaunchAdapter? = null

    // List
    var arrayList_launch: ArrayList<LaunchDto>? = null





    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_MainMenu doCreate")
    }

    override fun setTheme() {
    }

    override fun setPresetAnimation(): PresetAnimation {
        return PresetAnimation.NONE
    }

    override fun setWindowTransitions() {
    }

    override fun setNFCReadMode(): NFCReadMode {
        return NFCReadMode.NONE
    }

    override fun initLayout() {

        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_MainMenu initLayout")

        /**
         * findViewById
         */
        rv_main = findViewById(R.id.rv_main) as RecyclerView
        toolbar = findViewById(R.id.toolbar) as Toolbar

        /**
         * setListener
         */
        setSupportActionBar(toolbar)


    }

    override fun initData() {

        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_MainMenu initData")

        arrayList_launch = ArrayList()

        /**
         * 리스트 생성
         */
        CreateMenuList()

        launchAdapter = LaunchAdapter(arrayList_launch)
        launchAdapter!!.setItemClick(onItemClick)

        rv_main!!.layoutManager = LinearLayoutManager(this)
        rv_main!!.adapter = launchAdapter

        
    }

    override fun displayLandscapeAfter() {

        // 네비게이션 바가 투명하다면, 바닥의 패딩은 네비게이션바 높이만큼 추가해줘야 한다. (가로일 때에는 0이다.)
        rv_main!!.setPadding(0, 0, 0, 0)

    }

    override fun displayPortraitAfter() {

//        // 노치가 있을 때만 네비게이션 바가 있다고 판단하는게 나을 것 같다..
//        if (XELSystemUtil.isDeviceNotchExist(this))
//        {
            // 네비게이션 바가 투명하다면, 바닥의 패딩은 네비게이션바 높이만큼 추가해줘야 한다.
            rv_main!!.setPadding(
                0,
                0,
                0,
                XELSystemUtil.getNavigationBarHeight(this)
            )
//        }
//        else
//        {
//            rv_main!!.setPadding(0, 0, 0, 0)
//        }

    }

    override fun initAfterLogic() {

        XELLogUtil.d_function(XELGlobalDefine.TAG, "Activity_MainMenu initAfterLogic")
        
    }

    /**
     * recyclerView itemClick
     */
    val onItemClick = (object : LaunchAdapter.ItemClick{
        override fun onClick(view: View?, position: Int) {

            XELLogUtil.d(
                XELGlobalDefine.TAG,
                "Activity_MainMenu LaunchAdapter onItemClick pos : $position"
            )

            when (position)
            {
                0 -> XELDialogUtil.Dialog_OkAndCancel(this@Activity_MainMenu,
                    "CommonDlg - 확인, 취소",
                    View.OnClickListener {
                        XELDialogUtil.closeWait(0)
                        XELLogUtil.i(
                            XELGlobalDefine.TAG,
                            "===== Activity_MainMenu LaunchAdapter onItemClick pos : $position / 확인"
                        )
                    },
                    View.OnClickListener {
                        XELDialogUtil.closeWait(0)
                        XELLogUtil.i(
                            XELGlobalDefine.TAG,
                            "===== Activity_MainMenu LaunchAdapter onItemClick pos : $position / 취소"
                        )
                    })

                1 -> XELDialogUtil.Dialog_OkOnly(
                    this@Activity_MainMenu,
                    "CommonDlg - 확인",
                    View.OnClickListener {
                        XELDialogUtil.closeWait(0)
                        XELLogUtil.i(
                            XELGlobalDefine.TAG,
                            "===== Activity_MainMenu LaunchAdapter onItemClick pos : $position / 확인"
                        )
                    }
                )

                2 -> XELDialogUtil.Dialog_OkAndCancelText(this@Activity_MainMenu,
                    "CommonDlg - 확인, 취소 이름 변경",
                    "테스트확인",
                    "테스트취소",
                    View.OnClickListener {
                        XELDialogUtil.closeWait(0)
                        XELLogUtil.i(
                            XELGlobalDefine.TAG,
                            "===== Activity_MainMenu LaunchAdapter onItemClick pos : $position / 확인"
                        )
                    },
                    View.OnClickListener {
                        XELDialogUtil.closeWait(0)
                        XELLogUtil.i(
                            XELGlobalDefine.TAG,
                            "===== Activity_MainMenu LaunchAdapter onItemClick pos : $position / 취소"
                        )
                    })

                3 -> XELDialogUtil.MaterialDialog_OkAndCancelText(this@Activity_MainMenu,
                    "메테리얼 디자인",
                    "CommonDlg - 메테리얼 디자인 (확인, 취소)",
                    "확인",
                    "취소",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(
                            this@Activity_MainMenu,
                            "확확11",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(
                            this@Activity_MainMenu,
                            "취취11",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                4 -> XELDialogUtil.MaterialDialog_OkOnlyText(this@Activity_MainMenu,
                    "메테리얼 디자인",
                    "CommonDlg - 메테리얼 디자인 (확인)",
                    "확인",
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(
                            this@Activity_MainMenu,
                            "취취22",
                            Toast.LENGTH_SHORT
                        ).show()
                    })

                5 -> {
                    val currentdate: String =
                        XELDateUtil.GetTodayWithFormat(XELDateUtil.DATEUTIL_FORMAT_YYYY_MM_DD)
                    Toast.makeText(this@Activity_MainMenu, currentdate, Toast.LENGTH_SHORT)
                        .show()
                }

                6 -> {
                    XELDialogUtil.LoadingDialog(this@Activity_MainMenu)
                    XELDialogUtil.closeWait(2000)
                }

                7 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        Intent intent_startLaunchAct = new Intent(Activity_MainMenu.this, Activity_Volley.class);
//                        startActivity(intent_startLaunchAct, ActivityOptions.makeSceneTransitionAnimation(Activity_MainMenu.this).toBundle());

//                        Intent intent_startLaunchAct = new Intent(Activity_MainMenu.this, Activity_Volley.class);
//                        startActivity(intent_startLaunchAct);
                    }

                    val sharedView: View = view!!.findViewById<View>(R.id.row_launch_iv_image) as ImageView
                    val transitionName = getString(R.string.testMoveTransition)
                    val intent_startLaunchAct =
                        Intent(this@Activity_MainMenu, Activity_Volley::class.java)
                    val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        this@Activity_MainMenu,
                        Pair.create(sharedView, transitionName)
                    )
                    startActivity(intent_startLaunchAct, transitionActivityOptions.toBundle())
                }

                8 -> {
                    val intent_startGlide =
                        Intent(this@Activity_MainMenu, Activity_Glide::class.java)

//                    // 애니메이션 추가
                    val transitionActivityOptions_glide =
                        ActivityOptions.makeSceneTransitionAnimation(
                            this@Activity_MainMenu
                        )
                    startActivity(intent_startGlide, transitionActivityOptions_glide.toBundle())
                }

                9 -> {
                    val intent_startNFC_WRITE =
                        Intent(this@Activity_MainMenu, Activity_NFCWrite::class.java)
                    startActivity(intent_startNFC_WRITE)
                }

                10 -> {
                    val intent_startNFC_READ =
                        Intent(this@Activity_MainMenu, Activity_NFCRead::class.java)
                    startActivity(intent_startNFC_READ)
                }

                11 -> {
                    val intent_startFILE_WRITE = Intent(
                        this@Activity_MainMenu,
                        Activity_FileWrite::class.java
                    )
                    startActivity(intent_startFILE_WRITE)
                }

                12 -> {
                    val intent_startFILE_READ =
                        Intent(this@Activity_MainMenu, Activity_FileRead::class.java)
                    startActivity(intent_startFILE_READ)
                }

                13 -> {
                    val intent_startIMAGEFILE_READ = Intent(
                        this@Activity_MainMenu,
                        Activity_ImageFileRead::class.java
                    )
                    startActivity(intent_startIMAGEFILE_READ)
                }

                14 ->
                {
                    var array_bottomPopup = ArrayList<PopupTestDto>()

                    for (i : Int in 0 until 5)
                    {
                        val popupDto = PopupTestDto(""+i, "테스트네임 "+i, null)
                        array_bottomPopup.add(popupDto)
                    }

                    val intent_start_XEL_BottomPopup = Intent(this@Activity_MainMenu, XELActivity_BottomPopup::class.java)
                    intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_RESULT_TAG, 14)
                    intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_VIEW_TITLE, "테스트 타이틀")
                    intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_VIEW_LIST, array_bottomPopup)

                    bottomPopupResultLauncher.launch(intent_start_XEL_BottomPopup)
                }

                15 -> {
                    val intent_startSkeleton = Intent(
                        this@Activity_MainMenu,
                        Activity_Skeleton::class.java
                    )
                    startActivity(intent_startSkeleton)
                }

                16 -> {
//                    val intent_startFragment = Intent(
//                        this@Activity_MainMenu,
//                        Activity_Fragment::class.java
//                    )
//                    startActivity(intent_startFragment)

                    val sharedView: View = view!!.findViewById<View>(R.id.row_launch_iv_image) as ImageView
                    val transitionName = getString(R.string.testMoveTransition)
                    val intent_startLaunchAct =
                        Intent(this@Activity_MainMenu, Activity_Fragment::class.java)
                    val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        this@Activity_MainMenu,
                        Pair.create(sharedView, transitionName)
                    )
                    startActivity(intent_startLaunchAct, transitionActivityOptions.toBundle())
                }

                17 -> {
                    val intent_startRoom = Intent(
                        this@Activity_MainMenu,
                        Activity_Room::class.java
                    )
                    startActivity(intent_startRoom)
                }

                18 -> {
                    val intent_startAlarmAndNotification = Intent(
                        this@Activity_MainMenu,
                        Activity_AlarmAndNotification::class.java
                    )
                    startActivity(intent_startAlarmAndNotification)
                }

                19 -> {
                    val intent_startProgressBar = Intent(
                        this@Activity_MainMenu,
                        Activity_ProgressBar::class.java
                    )
                    startActivity(intent_startProgressBar)
                }
            }
        }
    })

    /**
     * 메뉴리스트 생성
     */
    private fun CreateMenuList() {
        // CommonDlg - 확인, 취소
        val model_0 = LaunchDto(null)
        model_0.menuName = "CommonDlg - 확인, 취소"
        arrayList_launch!!.add(model_0)

        // CommonDlg - 확인, 취소
        val model_1 = LaunchDto(null)
        model_1.menuName = "CommonDlg - 확인"
        arrayList_launch!!.add(model_1)

        // CommonDlg - 확인, 취소 이름 변경
        val model_2 = LaunchDto(null)
        model_2.menuName = "CommonDlg - 확인, 취소 이름 변경"
        arrayList_launch!!.add(model_2)

        // CommonDlg - 메테리얼 디자인 (확인, 취소)
        val model_21 = LaunchDto(null)
        model_21.menuName = "CommonDlg - 메테리얼 디자인 (확인, 취소)"
        arrayList_launch!!.add(model_21)

        // CommonDlg - 메테리얼 디자인 (확인)
        val model_22 = LaunchDto(null)
        model_22.menuName = "CommonDlg - 메테리얼 디자인 (확인)"
        arrayList_launch!!.add(model_22)

        // DateUtil - 오늘 날짜 구하기
        val model_3 = LaunchDto(null)
        model_3.menuName = "DateUtil - 오늘 날짜 구하기"
        arrayList_launch!!.add(model_3)

        // DialogUtil - 로딩 다이얼로그
        val model_4 = LaunchDto(null)
        model_4.menuName = "DialogUtil - 로딩 다이얼로그"
        arrayList_launch!!.add(model_4)

        // Volley - 네트워크 라이브러리
        val model_5 = LaunchDto(null)
        model_5.menuName = "Volley - 네트워크 라이브러리"
        arrayList_launch!!.add(model_5)

        // Glide - 이미지 라이브러리
        val model_6 = LaunchDto(null)
        model_6.menuName = "Glide - 이미지 라이브러리"
        arrayList_launch!!.add(model_6)

        // NFC WRITE
        val model_7 = LaunchDto(null)
        model_7.menuName = "NFC WRITE"
        arrayList_launch!!.add(model_7)

        // NFC READ
        val model_8 = LaunchDto(null)
        model_8.menuName = "NFC READ"
        arrayList_launch!!.add(model_8)

        // FILE WRITE
        val model_9_1 = LaunchDto(null)
        model_9_1.menuName = "FILE WRITE"
        arrayList_launch!!.add(model_9_1)

        // FILE READ
        val model_9 = LaunchDto(null)
        model_9.menuName = "FILE READ"
        arrayList_launch!!.add(model_9)

        // IMAGE FILE READ
        val model_10 = LaunchDto(null)
        model_10.menuName = "IMAGE FILE READ"
        arrayList_launch!!.add(model_10)

        // XEL Bottom Popup
        val model_11 = LaunchDto(null)
        model_11.menuName = "XEL Bottom Popup"
        arrayList_launch!!.add(model_11)

        // Skeleton
        val model_12 = LaunchDto(null)
        model_12.menuName = "Skeleton Sample"
        arrayList_launch!!.add(model_12)

        // Fragment
        val model_13 = LaunchDto(null)
        model_13.menuName = "Fragment"
        arrayList_launch!!.add(model_13)

        // Room
        val model_14 = LaunchDto(null)
        model_14.menuName = "Room"
        arrayList_launch!!.add(model_14)

        // Alarm And Notification
        val model_15 = LaunchDto(null)
        model_15.menuName = "Alarm And Notification"
        arrayList_launch!!.add(model_15)

        // ProgressBar
        val model_16 = LaunchDto(null)
        model_16.menuName = "ProgressBar Library"
        arrayList_launch!!.add(model_16)
    }


    var bottomPopupResultLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback { result ->
            if (result.resultCode == Activity.RESULT_OK)
            {

                // 결과값 태그, 포지션
                val tag = result.data?.getIntExtra(XELActivity_BottomPopup.BOTTOMPOPUP_RESULT_TAG, -1)
                val position = result.data?.getIntExtra(XELActivity_BottomPopup.BOTTOMPOPUP_RESULT_POSITION, -1)

                XELLogUtil.d_function(XELGlobalDefine.TAG, "ResultLauncher Data : $position")

                when (tag)
                {
                    14 ->
                    {
                        Toast.makeText(this, "SELECT POS : " + position, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    )


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