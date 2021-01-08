package org.mydaily.data.repository

import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import org.mydaily.data.remote.datasource.ReportRemoteDataSource
import retrofit2.Call

class ReportRepoImpl(private val remoteDataSource: ReportRemoteDataSource) : ReportRepo {
    override fun getReports(start: String, end: String): Call<ResReportGet> =
        remoteDataSource.getReports(start, end)

    override fun getReportDetails(start: String, end: String): Call<ResReportDetailGet> =
        remoteDataSource.getReportDetails(start, end)
}