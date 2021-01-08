package org.mydaily.data.remote.api

import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import retrofit2.Call
import retrofit2.http.*

interface ReportService {
    //GET	리포트 조회
    @GET("/reports")
    @Headers("Content-Type: application/json")
    fun getReports(
        @Header("jwt") jwt: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Call<ResReportGet>

    //POST	키워드별 리포트 조회
    @POST("/reports/detail")
    @Headers("Content-Type: application/json")
    fun getReportDetails(
        @Header("jwt") jwt: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Call<ResReportDetailGet>
}