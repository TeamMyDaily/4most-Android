package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import retrofit2.Call

interface ReportRepo {
    fun getReports(start: Long, end: Long): Call<ResReportGet>
    fun getReportDetails(body: ReqReportDetailGet): Call<ResReportDetailGet>
}