package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    //POST	회원가입
    @POST("/users/signup")
    @Headers("Content-Type: application/json")
    fun postSignUp(
        @Body body: ReqSignUp
    ): Call<ResSignUp>

    //POST	로그인
    @POST("/users/signin")
    @Headers("Content-Type: application/json")
    fun postSignIn(
        @Body body: ReqSignIn
    ): Call<ResSignIn>

    //POST	비밀번호 변경
    @POST("/users/password")
    @Headers("Content-Type: application/json")
    fun postPassword(
        @Body body: ReqPassword
    ): Call<Response>

    //POST	현재 비밀번호 일치여부
    @POST("/users/currentPassword")
    @Headers("Content-Type: application/json")
    fun postCurrentPassword(
        @Body body: ReqPassword
    ): Call<ResCurrentPassword>

    //DELETE	회원탈퇴
    @DELETE("/users")
    @Headers("Content-Type: application/json")
    fun deleteUser(
        @Body body: ReqPassword
    ): Call<Response>

    //GET	마이페이지
    @GET("/users")
    @Headers("Content-Type: application/json")
    fun getUser(
    ): Call<ResUser>
}