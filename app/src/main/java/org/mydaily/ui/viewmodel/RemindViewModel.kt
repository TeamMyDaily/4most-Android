package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.response.ResReportDetailGet
import org.mydaily.data.model.network.response.ResReportGet
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.repository.ReportRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemindViewModel(private val repo: ReportRepo): BaseViewModel() {
    private val _reportList = MutableLiveData<List<ResReportGet.Data.Result>>()
    val reportList: LiveData<List<ResReportGet.Data.Result>>
        get() = _reportList

    private val _reportDetailList = MutableLiveData<ResReportDetailGet.Data>()
    val reportDetailList: LiveData<ResReportDetailGet.Data>
        get() = _reportDetailList

    fun getReport(start: Long, end: Long) {
        repo.getReports(start, end).enqueue(object : Callback<ResReportGet> {
            override fun onResponse(call: Call<ResReportGet>, response: Response<ResReportGet>) {
                if (response.isSuccessful) {
                    _reportList.postValue(response.body()?.data?.result)
                    Log.e(TAG, "$start ->" + response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResReportGet>, t: Throwable) {
                Log.e(TAG, "$start ->" + "getReport", t)
            }
        })
    }

    fun getReportDetail(body: ReqReportDetailGet) {
        repo.getReportDetails(body).enqueue(object : Callback<ResReportDetailGet> {
            override fun onResponse(call: Call<ResReportDetailGet>, response: Response<ResReportDetailGet>) {
                if (response.isSuccessful) {
                    _reportDetailList.postValue(response.body()?.data)
                    Log.e(TAG, "result->" + response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResReportDetailGet>, t: Throwable) {
                Log.e(TAG, "fail->" + "getReportDetails", t)
            }
        })
    }

    companion object{
        private val TAG=RemindViewModel::class.java.simpleName
    }
}