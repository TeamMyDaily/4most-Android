package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import retrofit2.Call
import retrofit2.http.*

interface ReportService {
    //GET	리포트 조회
    @GET("/reports")
    @Headers("Content-Type: application/json")
    fun getReports(
        @Query("start") start: Long,
        @Query("end") end: Long
    ): Call<ResReportGet>

    //POST	키워드별 리포트 조회
    @POST("/reports/detail")
    @Headers("Content-Type: application/json")
    fun getReportDetails(
        @Body body : ReqReportDetailGet
    ): Call<ResReportDetailGet>
}