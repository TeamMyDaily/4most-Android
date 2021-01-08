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

    override fun postPassword(jwt: String, body: ReqPassword): Call<Response> =
        remoteDataSource.postPassword(jwt, body)

    override fun postCurrentPassword(jwt: String, body: ReqPassword): Call<ResCurrentPassword> =
        remoteDataSource.postCurrentPassword(jwt, body)

    override fun deleteUser(jwt: String, body: ReqPassword): Call<Response> =
        remoteDataSource.deleteUser(jwt, body)

    override fun getUser(jwt: String): Call<ResUser> = remoteDataSource.getUser(jwt)
}