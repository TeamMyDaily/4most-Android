package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import org.mydaily.data.remote.datasource.ReportRemoteDataSource
import retrofit2.Call

class ReportRepoImpl(private val remoteDataSource: ReportRemoteDataSource) : ReportRepo {
    override fun getReports(start: Long, end: Long): Call<ResReportGet> =
        remoteDataSource.getReports(start, end)

    override fun getReportDetails(body: ReqReportDetailGet): Call<ResReportDetailGet> =
        remoteDataSource.getReportDetails(body)
}