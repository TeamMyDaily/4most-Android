package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import retrofit2.Call

interface ReportRemoteDataSource {
    fun getReports(start: String, end: String): Call<ResReportGet>
    fun getReportDetails(start: String, end: String): Call<ResReportDetailGet>
}