package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.gson.annotations.SerializedName
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonApplication.XELGlobalDefine
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.CommonBase.XELActivity_Base
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELLogUtil
import com.xelpine.xel_android_kotlin_commonlibrary.CommonUtils.XELRetrofitUtils
import com.xelpine.xel_android_kotlin_commonlibrary.R
import com.xelpine.xel_android_kotlin_commonlibrary.databinding.ActivityRetrofitBinding
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.toviewinterface.RetrofitInterface
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodelfactory.ViewModelFactory_Retrofit
import com.xelpine.xel_android_kotlin_commonlibrary.z_customcode.viewmodel.viewmodels.ViewModel_Retrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*


class Activity_Retrofit : XELActivity_Base(), RetrofitInterface
{
    // DataBinding
    private lateinit var mBinding: ActivityRetrofitBinding
    // ViewModel
    private val mViewModel: ViewModel_Retrofit by viewModels{ ViewModelFactory_Retrofit(application, this) }

    // Adapter
//    lateinit var RetrofitListAdapter : RetrofitListAdapter

    override fun doCreate(savedInstanceState: Bundle?) {
        super.doCreate(savedInstanceState)

        // 데이터 바인딩
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit)

        mBinding.lifecycleOwner = this
        mBinding.retrofitViewModel = mViewModel
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
    }

    override fun initData() {
    }

    override fun displayLandscapeAfter() {
    }

    override fun displayPortraitAfter() {
    }

    override fun initAfterLogic() {


        /**
         * GET
         */
        val service = XELRetrofitUtils("https://jsonplaceholder.typicode.com/").create()

//        service.testGet("asdgasd", "tttget").enqueue(object : Callback<ArrayList<RetrofitComments>>{
//            override fun onResponse(call: Call<ArrayList<RetrofitComments>>, response: Response<ArrayList<RetrofitComments>>)
//            {
//                if (response.isSuccessful)
//                {
//                    var result: ArrayList<RetrofitComments>? = response.body()
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "service onResponse 성공: " + result?.get(0).toString())
//                }
//                else
//                {
//                    XELLogUtil.e_function(XELGlobalDefine.TAG, "service onResponse 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<ArrayList<RetrofitComments>>, t: Throwable) {
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "service onFailure 실패 : ${t.message.toString()}")
//            }
//        })

        /**
         * RxKotlin + Retrofit2
         */
        var test = service.testGet("asdgasd", "tttget")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { items ->
                    items.forEach {

                        XELLogUtil.i_function(XELGlobalDefine.TAG, "onNext " + it)
                    }

                },
                onComplete = { XELLogUtil.i_function(XELGlobalDefine.TAG, "onComplete")},
                onError = {e ->
                    println(e.toString())
                }
            )




//        /**
//         * POST
//         */
//        val service2 = XELRetrofitUtils("https://jsonplaceholder.typicode.com/").create()
//
//        // Body
//        val postsBody: RetrofitPosts = RetrofitPosts(
//            10, 100, "TEST TITLE", "TEST BODY"
//        )
//
//        service2.testPost(postsBody).enqueue(object : Callback<RetrofitPosts>{
//            override fun onResponse(call: Call<RetrofitPosts>, response: Response<RetrofitPosts>) {
//
//                if (response.isSuccessful)
//                {
//                    var result: RetrofitPosts? = response.body()
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "service2 onResponse 성공: " + result?.toString());
//                }
//                else
//                {
//                    XELLogUtil.e_function(XELGlobalDefine.TAG, "service2 onResponse 실패")
//                }
//
//            }
//
//            override fun onFailure(call: Call<RetrofitPosts>, t: Throwable) {
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "service2 onResponse 실패 : ${t.message.toString()}")
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "service2 onResponse 실패 : ${call.toString()}")
//            }
//        })
//
//
//        /**
//         * POST + Field
//         */
//        val service3 = XELRetrofitUtils("https://jsonplaceholder.typicode.com/").create()
//
//        service3.testPostWithUrlEncoded(50, "service3 TITLE", "service3 BODY").enqueue(object : Callback<RetrofitPosts>{
//            override fun onResponse(call: Call<RetrofitPosts>, response: Response<RetrofitPosts>) {
//
//                if (response.isSuccessful)
//                {
//                    var result: RetrofitPosts? = response.body()
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "service3 onResponse 성공: " + result?.toString());
//                }
//                else
//                {
//                    XELLogUtil.e_function(XELGlobalDefine.TAG, "service3 onResponse 실패")
//                }
//
//            }
//
//            override fun onFailure(call: Call<RetrofitPosts>, t: Throwable) {
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "service3 onResponse 실패 : ${t.message.toString()}")
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "service3 onResponse 실패 : ${call.toString()}")
//            }
//        })
    }

    override fun doStart() {
    }

    override fun doResume() {
    }

    override fun doPause() {
    }

    override fun doStop() {
    }

    override fun doDestroy() {
    }
}

data class RetrofitComments(
    @SerializedName("postId")
    val postId: Int,

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("body")
    val body: String
    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
)
{
    override fun toString(): String {

        return "PostResult{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';

    }
}

data class RetrofitPosts(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val body: String
)

//RetorifitService.kt
interface RetrofitService {

    //GET 예제 : 데이터 길이 제한 있음 (255바이트)
    @GET("posts/1/comments")
    fun testGet(
        // Query : & 뒤에 파라미터로 접근가능하게 해줌
        // QueryMap : 이런 것들을 하나의 묶음으로 가능하도록 해줌
        //
        @Query("userId") userid : String,
        @Query("ttt") ttt : String,
    ): Observable<ArrayList<RetrofitComments>>

    // POST : 데이터를 BODY에 담아 전송하는 방식, 데이터 제한 없음
    @POST("posts")
    fun testPost(
        @Body requestBody : RetrofitPosts
    ): Call<RetrofitPosts>

    // POST + Field : UrlEncode 방식으로 전달함
    @FormUrlEncoded
    @POST("posts")
    fun testPostWithUrlEncoded(
        @Field("userId") userId : Int,
        @Field("title") title : String,
        @Field("body") body : String
    ): Call<RetrofitPosts>

    @GET("posts/{page}")
    fun getUserPage(@Path("page") page: String): Call<RetrofitComments>




//    @GET("posts/1")
//    fun getStudent(@Query("school_id") schoolId: Int,
//                   @Query("grade") grade: Int,
//                   @Query("classroom") classroom: Int): Call<ExampleResponse>
//
//
//    //POST 예제
//    @FormUrlEncoded
//    @POST("posts")
//    fun getContactsObject(@Field("idx") idx: String): Call<JsonObject>
}