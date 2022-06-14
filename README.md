# XEL_Android_Kot_CommonLibrary

XELPINE 안드로이드 코틀린용 개인 라이브러리입니다.
  
개인적으로 개발하며 필요하며 만들었던 BaseActivity, BaseFragment, BottomSheetPopup, Google Volley 등의 라이브러리 조합을 하나의 샘플로 만들었습니다.
  
제 개인 앱들은 대부분 이 베이스를 기반으로 만들어지며, 이 라이브러리 역시 그때그떄 업데이트됩니다.
  
# ReadMe
CommonUtils 까지가 기본 라이브러리 패키지 영역, z_customcode 영역은 라이브러리를 기반으로 한 샘플코드들의 패키지 영역입니다.

# MainMenu

<img width="100%" src="https://user-images.githubusercontent.com/74861834/170941410-62e1b5b7-80b5-42a8-8670-cf5570344957.png"/>

가장 첫화면에서 샘플코드들의 메뉴로 이동할 수 있습니다.


# Dialog
<img width="45%" src="https://user-images.githubusercontent.com/74861834/170941475-b2bb7c24-e009-415a-a6e2-6e9ada94c350.png"/>
<img width="45%" src="https://user-images.githubusercontent.com/74861834/170941465-128d6e60-9720-430e-ab87-ac696fe61a5b.png"/>

XELDialogUtil을 통해 Material Design 기반의 다이얼로그와 이전 테마 기반의 다이얼로그를 모두 호출할 수 있습니다.

ClickListener가 내장되어 callback을 받기 유용합니다. 추후 람다식으로 변경 예정입니다.

<pre><code>XELDialogUtil.Dialog_OkAndCancel  

XELDialogUtil.Dialog_OkOnly  

XELDialogUtil.Dialog_OkOnly  

XELDialogUtil.MaterialDialog_OkAndCancelText  

XELDialogUtil.MaterialDialog_OkOnlyText  </code></pre>
  
등을 활용할 수 있습니다.
  
다이얼로그 닫기는 아래 메소드를 활용하십시오.
  
<pre><code>XELDialogUtil.closeWait</code></pre>


# XELBottomPopup

<img width="100%" src="https://user-images.githubusercontent.com/74861834/170941424-053ccd52-752c-4da3-9c2a-9303bebaedbc.png"/>

아래에서 솟아오르는 형태의 팝업입니다.

기본적으로 XELCommonSelectionInterface를 포함한 클래스의 arrayList를 전달해줘야 하며,

인터페이스를 implements 하여 Code, Name, NameWithSelector을 구현하면 리스트에 나타납니다.

<pre><code>val intent_start_XEL_BottomPopup = Intent(this@Activity_MainMenu, XELActivity_BottomPopup::class.java)
intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_RESULT_TAG, 14)
intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_VIEW_TITLE, "테스트 타이틀")
intent_start_XEL_BottomPopup.putExtra(XELActivity_BottomPopup.BOTTOMPOPUP_VIEW_LIST, array_bottomPopup)
bottomPopupResultLauncher.launch(intent_start_XEL_BottomPopup)</code></pre>

형태로 실행할 수 있으며, registerForActivityResult를 통해 수신할 수 있습니다.

XELBottomPopup은 Material Design 가이드를 준수하며 제작되었으며, DayNight Theme를 지원합니다.


# XELActivity_Base

베이스 액티비티입니다. AppCompatActivity를 상속받아 커스텀된 구조이며, 추상클래스 구조이기에 일부 메소드를 구현해야 합니다.



<pre><code>setTheme()</code></pre>

테마가 변경될 경우 불려지는 메소드입니다. 테마 변경 후 setContentView가 호출되기 전에 필요한 작업을 기록하십시오.




<pre><code>setWindowTransitions를()</code></pre>

Android Transition Animation을 구성하는 부분입니다. Transition Animation 구성 시, setWindowTransitions를 무시하게 됩니다.




<pre><code>setPresetAnimation()</code></pre>

프리셋으로 만들어진 애니메이션을 지정합니다. NONE, SLIDE_RIGHT, SLIDE_BOTTOM, FADE가 현재 개발되어 있으며, 애니메이션을 지정할 Activity에서 코드를 반드시 지정해야 합니다.
