package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqPassword
import org.mydaily.data.model.network.request.ReqSignIn
import org.mydaily.data.model.network.request.ReqSignUp
import org.mydaily.data.model.network.response.*
import org.mydaily.data.remote.datasource.UserRemoteDataSource
import retrofit2.Call

class UserRepoImpl(private val remoteDataSource: UserRemoteDataSource) : UserRepo {
    override fun postSignUp(body: ReqSignUp): Call<ResSignUp> = remoteDataSource.postSignUp(body)

    override fun postSignIn(body: ReqSignIn): Call<ResSignIn> = remoteDataSource.postSignIn(body)

    override fun postPassword(body: ReqPassword): Call<Response> =
        remoteDataSource.postPassword(body)

    override fun postCurrentPassword(body: ReqPassword): Call<ResCurrentPassword> =
        remoteDataSource.postCurrentPassword(body)

    override fun deleteUser(body: ReqPassword): Call<Response> =
        remoteDataSource.deleteUser(body)

    override fun getUser(): Call<ResUser> = remoteDataSource.getUser()
}