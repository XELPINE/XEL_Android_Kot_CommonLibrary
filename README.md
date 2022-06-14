# XEL_Android_Kot_CommonLibrary

XELPINE 안드로이드 코틀린용 개인 라이브러리입니다.
  
개인적으로 개발하며 필요하며 만들었던 BaseActivity, BaseFragment, BottomSheetPopup, Google Volley 등의 라이브러리 조합을 하나의 샘플로 만들었습니다.
  
제 개인 앱들은 대부분 이 베이스를 기반으로 만들어지며, 이 라이브러리 역시 그때그떄 업데이트됩니다.


<br>
<br>
<br>

# ReadMe
CommonUtils 까지가 기본 라이브러리 패키지 영역, z_customcode 영역은 라이브러리를 기반으로 한 샘플코드들의 패키지 영역입니다.


<br>
<br>
<br>

# MainMenu


<table align="center">
  <tr>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/170941410-62e1b5b7-80b5-42a8-8670-cf5570344957.png"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>Sample Main Page</b>
    </td>
  </tr>
</table>


가장 첫화면에서 샘플코드들의 메뉴로 이동할 수 있습니다.


<br>
<br>
<br>

# Dialog

<table align="center">
  <tr>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/170941475-b2bb7c24-e009-415a-a6e2-6e9ada94c350.png"/>
    </td>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/170941465-128d6e60-9720-430e-ab87-ac696fe61a5b.png"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>Dialog</b>
    </td>
    <td align="center">
      <b>MaterialDialog</b>
    </td>
  </tr>
</table>



XELDialogUtil을 통해 Material Design 기반의 다이얼로그와 이전 테마 기반의 다이얼로그를 모두 호출할 수 있습니다.

ClickListener가 내장되어 callback을 받기 유용합니다. 추후 람다식으로 변경 예정입니다.

<pre><code>XELDialogUtil.Dialog_OkAndCancel  

XELDialogUtil.Dialog_OkOnly  

XELDialogUtil.Dialog_OkOnly  

XELDialogUtil.MaterialDialog_OkAndCancelText  

XELDialogUtil.MaterialDialog_OkOnlyText</code></pre>
  
등을 활용할 수 있습니다.
  
다이얼로그 닫기는 아래 메소드를 활용하십시오.
  
<pre><code>XELDialogUtil.closeWait()</code></pre>

파라미터로 종료 전 타이머를 줄 수 있으며, 0을 넣을 경우 기본적으로 바로 닫힙니다.


<br>
<br>
<br>

# XELBottomPopup

<table align="center">
  <tr>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/173469929-ae704593-d097-4706-b4d2-92bed8d9f6da.gif"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>XELBottomPopup Sample</b>
    </td>
  </tr>
</table>



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

<br>
<br>
<br>

# XELActivity_Base

베이스 액티비티입니다. AppCompatActivity를 상속받아 커스텀된 구조이며, 추상클래스 구조이기에 일부 메소드를 구현해야 합니다.

<br>
<br>

<pre><code>setTheme()</code></pre>

테마가 변경될 경우 불려지는 메소드입니다. 테마 변경 후 setContentView가 호출되기 전에 필요한 작업을 기록하십시오.


<br>
<br>

<pre><code>setWindowTransitions()</code></pre>

<table align="center">
  <tr>
    <td align="center">
<img width="50%" src="https://user-images.githubusercontent.com/74861834/173474030-e3955971-d2b9-40e6-b421-0fb0e7292e26.gif"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>setWindowTransitions Sample</b>
    </td>
  </tr>
</table>

Android Transition Animation을 구성하는 부분입니다. Transition Animation 구성 시, setWindowTransitions를 무시하게 됩니다.


<br>
<br>

<pre><code>setPresetAnimation(): PresetAnimation?</code></pre>

<table>
  <tr>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/173471534-78cd8744-d020-4458-95d0-21488b2ef006.gif"/>
    </td>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/173469929-ae704593-d097-4706-b4d2-92bed8d9f6da.gif"/>
    </td>
    <td align="center">
<img width="100%" src="https://user-images.githubusercontent.com/74861834/173469936-8f3c219c-6133-4f97-8f03-043f7917eecd.gif"/>
    </td>
  </tr>
  <tr>
    <td align="center">
      <b>FADE</b>
    </td>
    <td align="center">
      <b>SLIDE_BOTTOM</b>
    </td>
        <td align="center">
      <b>SLIDE_RIGHT</b>
    </td>
  </tr>
</table>

프리셋으로 만들어진 애니메이션을 지정합니다. NONE, SLIDE_RIGHT, SLIDE_BOTTOM, FADE가 현재 개발되어 있으며, 애니메이션을 지정할 Activity에서 코드를 반드시 지정해야 합니다.

<br>

<pre><code>setNFCReadMode(): NFCReadMode?</code></pre>

NFC 읽기 모드를 해당 액티비티에서 활성화할 지를 지정합니다.

NONE일 경우 읽지 않으며, READ일 경우 활성화되며 성공적으로 읽혔을 경우 ```readNFC(strNfc: String)``` 로 데이터가 전달됩니다.

<br>
<br>

<pre><code>doCreate(savedInstanceState: Bundle?)</code></pre>

```setTheme()```, ```setWindowsTransition()``` 등의 메소드가 호출된 후 화면을 그리기 시작하기 전 불리는 메소드입니다.

Android AAC를 사용하거나 할 경우 DataBinding 등의 작업을 하기에 좋습니다.

<br>
<br>

<pre><code>initLayout()</code></pre>

레이아웃 초기화 관련 코드를 넣거나 작업합니다.

<br>
<br>

<pre><code>initData()</code></pre>

데이터 관련 작업을 진행합니다. ViewModel의 데이터들을 Observe하는 등의 코드를 작성합니다.

<br>
<br>

<pre><code>displayLandscapeAfter()</code></pre>

디바이스가 가로로 변경되었을 때 동작합니다.

이 동작은, 액티비티 진입 시 처음부터 화면이 가로일 떄도 호출됩니다.

<br>
<br>

<pre><code>displayPortraitAfter()</code></pre>

디바이스가 세로로 변경되었을 때 동작합니다.

마찬가지로, 액티비티 진입 시 처음부터 화면이 세로일 떄도 호출됩니다.

<br>
<br>

<pre><code>initAfterLogic()</code></pre>

모든 초기화가 완료된 후 business Logic을 구현하는 부분입니다.

<br>
<br>

<pre><code>onDataResponseSucess(tag: String, data: String)</code></pre>

같이 개발된 Google 네트워크 라이브러리를 커스텀한 XELVolleyUtil을 사용해 volley request 시 성공한 경우 불려지는 메소드입니다.

```XELVolleyUtil.startStringRequestData()``` 메소드로 호출되며, 성공한 경우 처음에 입력한 tag를 기반으로 수신값을 확인할 수 있으며, 비동기적으로 동작합니다.

<br>
<br>

<pre><code>onDataResponseError(tag: String, errorCode: Int)</code></pre>

volley request 실패 시 전달됩니다. 기본 정의된 errorCode가 첨부됩니다.

<br>
<br>

<pre><code>onDataException(e: Exception)</code></pre>

volley request 처리가 요청단계에서 실패 시 불립니다. 현재 사용되지 않습니다.



