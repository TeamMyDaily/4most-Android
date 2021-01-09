package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import retrofit2.Call

interface UserRemoteDataSource {
    fun postSignUp(body: ReqSignUp): Call<ResSignUp>
    fun postSignIn(body: ReqSignIn): Call<ResSignIn>
    fun postPassword(body: ReqPassword): Call<Response>
    fun postCurrentPassword(body: ReqPassword): Call<ResCurrentPassword>
    fun deleteUser(body: ReqPassword): Call<Response>
    fun getUser(): Call<ResUser>
}