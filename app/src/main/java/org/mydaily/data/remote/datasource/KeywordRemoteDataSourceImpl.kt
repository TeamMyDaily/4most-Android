package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.*
import org.mydaily.data.remote.api.KeywordService
import retrofit2.Call

class KeywordRemoteDataSourceImpl(private val service: KeywordService) : KeywordRemoteDataSource {
    override fun postKeywordSelect(body: ReqKeywordSelect): Call<ResKeywordSelect> =
        service.postKeywordSelect(body)

    override fun postKeywordAdd(body: ReqKeywordAdd): Call<ResKeywordAdd> =
        service.postKeywordAdd(body)

    override fun deleteKeyword(body: ReqKeywordDelete): Call<Response> =
        service.deleteKeyword(body)

    override fun postKeywordDefinition(body: ReqKeywordDefine): Call<Response> =
        service.postKeywordDefinition(body)

    override fun postKeywordPriority(body: ReqKeywordPriority): Call<Response> =
        service.postKeywordPriority(body)

    override fun getTaskKeyword(): Call<ResTaskKeywordGet> = service.getTaskKeyword()

    override fun getKeywordList(): Call<ResKeywordListGet> = service.getKeywordList()

    override fun getKeywordDefinition(totalKeywordId: Int): Call<ResKeywordDefinitionGet>
    = service.getKeywordDefinition(totalKeywordId)
}