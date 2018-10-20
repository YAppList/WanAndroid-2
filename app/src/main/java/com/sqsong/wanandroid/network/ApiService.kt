package com.sqsong.wanandroid.network

import com.sqsong.wanandroid.data.BaseData
import com.sqsong.wanandroid.data.HomeBannerBean
import com.sqsong.wanandroid.data.HomeItemBean
import com.sqsong.wanandroid.data.LoginBean
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    // 首页banner
    @GET("banner/json")
    fun getHomeBanner(): Observable<HomeBannerBean>

    // 首页文章列表
    @GET("article/list/{page}/json")
    fun getHomeDataList(@Path("page") page: Int): Observable<HomeItemBean>

    // 注册
    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") userName: String, @Field("password") password: String,
                 @Field("repassword") rePassword: String): Observable<BaseData>

    // 登录
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String)
            : Observable<LoginBean>
}