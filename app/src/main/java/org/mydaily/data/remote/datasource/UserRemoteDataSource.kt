package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import retrofit2.Call

interface UserRemoteDataSource {
    fun postSignUp(body: ReqSignUp): Call<ResSignUp>
    fun postSignIn(body: ReqSignIn): Call<ResSignIn>
    fun postPassword(jwt: String, body: ReqPassword): Call<Response>
    fun postCurrentPassword(jwt: String, body: ReqPassword): Call<ResCurrentPassword>
    fun deleteUser(jwt: String, body: ReqPassword): Call<Response>
    fun getUser(jwt: String): Call<ResUser>
}