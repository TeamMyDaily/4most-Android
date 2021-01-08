package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.ResKeywordAdd
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.Response
import retrofit2.Call

interface KeywordRemoteDataSource {
    fun postKeywordSelect(body: ReqKeywordSelect): Call<ResKeywordSelect>
    fun postKeywordAdd(body: ReqKeywordAdd): Call<ResKeywordAdd>
    fun deleteKeyword(body: ReqKeywordDelete): Call<Response>
    fun postKeywordDefinition(body: ReqKeywordDefine): Call<Response>
    fun postKeywordPriority(body: ReqKeywordPriority): Call<Response>
}