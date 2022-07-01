package com.xelpine.xel_android_kotlin_commonlibrary.z_customcode

import android.os.Bundle
import android.util.Log
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

//        val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
//            .addConverterFactory(GsonConverterFactory.create()).build();
        val service = XELRetrofitUtils("https://httpbin.org/").retrofitInstance
            .create(RetrofitService::class.java)

        // GET
//        service.getComments().enqueue(object : Callback<ArrayList<RetrofitComments>>{
//            override fun onResponse(call: Call<ArrayList<RetrofitComments>>, response: Response<ArrayList<RetrofitComments>>) {
//                var result: ArrayList<RetrofitComments>? = response.body()
//                XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result?.toString());
//
//                for (index : Int in 0 until result!!.size)
//                {
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).postId)
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).id)
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).name)
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).email)
//                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).body)
//                }
//            }
//
//            override fun onFailure(call: Call<ArrayList<RetrofitComments>>, t: Throwable) {
//                XELLogUtil.e_function(XELGlobalDefine.TAG, "onResponse 실패 : ${t.message.toString()}")
//            }
//        })

        // POST
        service.postComments("234ad").enqueue(object : Callback<RetrofitPosts>{
            override fun onResponse(call: Call<RetrofitPosts>, response: Response<RetrofitPosts>) {
                var result: RetrofitPosts? = response.body()
                XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result?.toString());
                XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result?.test)

//                for (index : Int in 0 until result!!.size)
//                {
////                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).postId)
////                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).id)
////                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).name)
////                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).email)
////                    XELLogUtil.i_function(XELGlobalDefine.TAG, "onResponse 성공: " + result.get(index).body)
//                }
            }

            override fun onFailure(call: Call<RetrofitPosts>, t: Throwable) {
                XELLogUtil.e_function(XELGlobalDefine.TAG, "onResponse 실패 : ${t.message.toString()}")
                XELLogUtil.e_function(XELGlobalDefine.TAG, "onResponse 실패 : ${call.toString()}")
            }
        })

//        service.getUserPage("1")?.enqueue(object : Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                if(response.isSuccessful){
//                    // 정상적으로 통신이 성고된 경우
//                    var result: User? = response.body()
//                    XELLogUtil.i_function("YMC", "onResponse 성공: " + result?.toString());
//                }else{
//                    // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
//                    XELLogUtil.i_function("YMC", "onResponse 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
//                XELLogUtil.e_function("YMC", "onFailure 에러: " + t.message.toString());
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
data class RetrofitPosts(
    @SerializedName("test")
    val test: String

    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
)

//RetorifitService.kt
interface RetrofitService {

    //GET 예제
    @GET("posts/1/comments")
    fun getComments(): Call<ArrayList<RetrofitComments>>

    @FormUrlEncoded
    @POST("post")
    fun postComments(@Field("test") test : String): Call<RetrofitPosts>

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