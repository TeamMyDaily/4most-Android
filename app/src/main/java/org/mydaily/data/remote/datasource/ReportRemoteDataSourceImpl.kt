package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import org.mydaily.data.remote.api.ReportService
import retrofit2.Call

class ReportRemoteDataSourceImpl(private val service: ReportService) : ReportRemoteDataSource {
    override fun getReports(start: Long, end: Long): Call<ResReportGet> =
        service.getReports(start, end)

    override fun getReportDetails(body: ReqReportDetailGet): Call<ResReportDetailGet> =
        service.getReportDetails(body)
}