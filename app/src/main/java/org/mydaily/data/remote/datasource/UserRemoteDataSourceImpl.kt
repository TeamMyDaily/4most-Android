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

    override fun postPassword(body: ReqPassword): Call<Response> =
        service.postPassword(body)

    override fun postCurrentPassword(body: ReqPassword): Call<ResCurrentPassword> =
        service.postCurrentPassword(body)

    override fun deleteUser(body: ReqPassword): Call<Response> =
        service.deleteUser(body)
}