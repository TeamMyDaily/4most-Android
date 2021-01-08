package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.ResKeywordAdd
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.Response
import retrofit2.Call

interface KeywordRemoteDataSource {
    fun postKeywordSelect(jwt: String, body: ReqKeywordSelect): Call<ResKeywordSelect>
    fun postKeywordAdd(jwt: String, body: ReqKeywordAdd): Call<ResKeywordAdd>
    fun deleteKeyword(jwt: String, body: ReqKeywordDelete): Call<Response>
    fun postKeywordDefinition(jwt: String, body: ReqKeywordDefine): Call<Response>
    fun postKeywordPriority(jwt: String, body: ReqKeywordPriority): Call<Response>
}