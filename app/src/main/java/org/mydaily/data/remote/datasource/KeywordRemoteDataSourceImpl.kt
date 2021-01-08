package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.ResKeywordAdd
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.api.KeywordService
import retrofit2.Call

class KeywordRemoteDataSourceImpl(private val service: KeywordService) : KeywordRemoteDataSource {
    override fun postKeywordSelect(jwt: String, body: ReqKeywordSelect): Call<ResKeywordSelect> =
        service.postKeywordSelect(jwt, body)

    override fun postKeywordAdd(jwt: String, body: ReqKeywordAdd): Call<ResKeywordAdd> =
        service.postKeywordAdd(jwt, body)

    override fun deleteKeyword(jwt: String, body: ReqKeywordDelete): Call<Response> =
        service.deleteKeyword(jwt, body)

    override fun postKeywordDefinition(jwt: String, body: ReqKeywordDefine): Call<Response> =
        service.postKeywordDefinition(jwt, body)

    override fun postKeywordPriority(jwt: String, body: ReqKeywordPriority): Call<Response> =
        service.postKeywordPriority(jwt, body)
}