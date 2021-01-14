package org.mydaily.data.repository

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.*
import retrofit2.Call

interface KeywordRepo {
    fun postKeywordSelect(body: ReqKeywordSelect): Call<ResKeywordSelect>
    fun postKeywordAdd(body: ReqKeywordAdd): Call<ResKeywordAdd>
    fun deleteKeyword(body: ReqKeywordDelete): Call<Response>
    fun postKeywordDefinition(body: ReqKeywordDefine): Call<Response>
    fun postKeywordPriority(body: ReqKeywordPriority): Call<Response>
    fun getTaskKeyword(): Call<ResTaskKeywordGet>
    fun getKeywordList(): Call<ResKeywordListGet>
    fun getKeywordDefinition(totalKeywordId: Int): Call<ResKeywordDefinitionGet>
}