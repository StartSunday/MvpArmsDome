package com.sun.common_login.mvp.model.api

import com.sun.common_login.mvp.model.entity.LoginBean
import com.sun.component.commonres.entity.BaseBean
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 *
 *
 * Created by ArmsComponentTemplate on 08/08/2019 09:26
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * [Star me](https://github.com/JessYanCoding/ArmsComponent)
 * [See me](https://github.com/JessYanCoding/ArmsComponent/wiki)
 * [模版请保持更新](https://github.com/JessYanCoding/ArmsComponent-Template)
 * ================================================
 */
interface Api{

    @FormUrlEncoded
    @POST("user/register")
    fun register(@FieldMap map: Map<String, String>): Observable<BaseBean<LoginBean>>


    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") index: String, @Field("password") map: String): Observable<BaseBean<LoginBean>>
}
