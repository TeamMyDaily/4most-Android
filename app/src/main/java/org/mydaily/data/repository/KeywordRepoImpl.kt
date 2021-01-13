package org.mydaily.data.repository

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.*
import org.mydaily.data.remote.datasource.KeywordRemoteDataSource
import retrofit2.Call

class KeywordRepoImpl(private val remoteDataSource: KeywordRemoteDataSource) : KeywordRepo {
    override fun postKeywordSelect(body: ReqKeywordSelect): Call<ResKeywordSelect> =
        remoteDataSource.postKeywordSelect(body)

    override fun postKeywordAdd(body: ReqKeywordAdd): Call<ResKeywordAdd> =
        remoteDataSource.postKeywordAdd(body)

    override fun deleteKeyword(body: ReqKeywordDelete): Call<Response> =
        remoteDataSource.deleteKeyword(body)

    override fun postKeywordDefinition(body: ReqKeywordDefine): Call<Response> =
        remoteDataSource.postKeywordDefinition(body)

    override fun postKeywordPriority(body: ReqKeywordPriority): Call<Response> =
        remoteDataSource.postKeywordPriority(body)

    override fun getTaskKeyword(): Call<ResTaskKeywordGet>  = remoteDataSource.getTaskKeyword()

    override fun getKeywordList(): Call<ResKeywordListGet>  = remoteDataSource.getKeywordList()
}