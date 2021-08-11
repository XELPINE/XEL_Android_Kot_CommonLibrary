package com.xelpine.xel_android_kotlin_commonlibrary

import android.app.ActivityOptions
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDateUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELDialogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode.*
import com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode.Adapter.LaunchAdapter
import com.xelpine.xel_android_kotlin_commonlibrary.Z_CustomCode.Dto.LaunchDto
import java.util.ArrayList

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

    override fun DisplayLandscapeAfter() {
    }

    override fun DisplayPortraitAfter() {
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

            when (position) {
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

        // FILE READ
        val model_10 = LaunchDto(null)
        model_10.menuName = "IMAGE FILE READ"
        arrayList_launch!!.add(model_10)
    }

    override fun doPause() {
    }

    override fun doResume() {
    }

    override fun doDestroy() {
    }
}