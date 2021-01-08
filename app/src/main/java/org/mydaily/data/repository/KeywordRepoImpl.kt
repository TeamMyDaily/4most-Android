package org.mydaily.data.repository

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.ResKeywordAdd
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.datasource.KeywordRemoteDataSource
import retrofit2.Call

class KeywordRepoImpl(private val remoteDataSource: KeywordRemoteDataSource) : KeywordRepo {
    override fun postKeywordSelect(jwt: String, body: ReqKeywordSelect): Call<ResKeywordSelect> =
        remoteDataSource.postKeywordSelect(jwt, body)

    override fun postKeywordAdd(jwt: String, body: ReqKeywordAdd): Call<ResKeywordAdd> =
        remoteDataSource.postKeywordAdd(jwt, body)

    override fun deleteKeyword(jwt: String, body: ReqKeywordDelete): Call<Response> =
        remoteDataSource.deleteKeyword(jwt, body)

    override fun postKeywordDefinition(jwt: String, body: ReqKeywordDefine): Call<Response> =
        remoteDataSource.postKeywordDefinition(jwt, body)

    override fun postKeywordPriority(jwt: String, body: ReqKeywordPriority): Call<Response> =
        remoteDataSource.postKeywordPriority(jwt, body)
}