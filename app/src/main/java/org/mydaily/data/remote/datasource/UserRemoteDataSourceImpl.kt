package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import org.mydaily.data.remote.api.UserService
import retrofit2.Call

class UserRemoteDataSourceImpl(private val service: UserService) : UserRemoteDataSource {
    override fun postSignUp(body: ReqSignUp): Call<ResSignUp> =
        service.postSignUp(body)

    override fun postSignIn(body: ReqSignIn): Call<ResSignIn> =
        service.postSignIn(body)

    override fun postPassword(jwt: String, body: ReqPassword): Call<Response> =
        service.postPassword(jwt, body)

    override fun postCurrentPassword(jwt: String, body: ReqPassword): Call<ResCurrentPassword> =
        service.postCurrentPassword(jwt, body)

    override fun deleteUser(jwt: String, body: ReqPassword): Call<Response> =
        service.deleteUser(jwt, body)

    override fun getUser(jwt: String): Call<ResUser> =
        service.getUser(jwt)
}