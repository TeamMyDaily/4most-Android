package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.*
import org.mydaily.data.model.network.response.ResKeywordAdd
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.Response
import retrofit2.Call
import retrofit2.http.*

interface KeywordService {

    //POST	키워드 선택
    @POST("/keywords")
    @Headers("Content-Type: application/json")
    fun postKeywordSelect(
        @Body body: ReqKeywordSelect
    ): Call<ResKeywordSelect>

    //POST	키워드 추가
    @POST("/keywords")
    @Headers("Content-Type: application/json")
    fun postKeywordAdd(
        @Body body: ReqKeywordAdd
    ): Call<ResKeywordAdd>

    //DELETE	사용자 키워드 삭제
    @DELETE("/keywords")
    @Headers("Content-Type: application/json")
    fun deleteKeyword(
        @Body body: ReqKeywordDelete
    ): Call<Response>

    //POST	키워드 정의 등록
    @POST("/keywords/definition")
    @Headers("Content-Type: application/json")
    fun postKeywordDefinition(
        @Body body: ReqKeywordDefine
    ): Call<Response>

    //POST	키워드 우선순위 등록
    @POST("/keywords/priority")
    @Headers("Content-Type: application/json")
    fun postKeywordPriority(
        @Body body: ReqKeywordPriority
    ): Call<Response>

}