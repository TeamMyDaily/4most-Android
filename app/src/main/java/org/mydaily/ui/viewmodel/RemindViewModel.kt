package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqReportDetailGet
import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.data.model.network.response.*
import org.mydaily.data.repository.ReportRepo
import org.mydaily.data.repository.ReviewRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemindViewModel(private val reportRepo: ReportRepo, private val reviewRepo: ReviewRepo): BaseViewModel() {
    private val _startEnd = MutableLiveData<List<Long>>()
    val startEnd: LiveData<List<Long>>
        get() = _startEnd

    private val _reportList = MutableLiveData<ResReportGet.Data>()
    val reportList: LiveData<ResReportGet.Data>
        get() = _reportList

    private val _reportDetailList = MutableLiveData<ResReportDetailGet.Data>()
    val reportDetailList: LiveData<ResReportDetailGet.Data>
        get() = _reportDetailList

    private val _reviewList = MutableLiveData<ResReviewGet.Data>()
    val reviewList: LiveData<ResReviewGet.Data>
        get() = _reviewList

    fun getReport(start: Long, end: Long) {
        reportRepo.getReports(start, end).enqueue(object : Callback<ResReportGet> {
            override fun onResponse(call: Call<ResReportGet>, response: Response<ResReportGet>) {
                if (response.isSuccessful) {
                    _reportList.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResReportGet>, t: Throwable) {
                Log.e(TAG, "$start ->" + "getReport", t)
            }
        })
    }

    fun getReportDetail(body: ReqReportDetailGet) {
        reportRepo.getReportDetails(body).enqueue(object : Callback<ResReportDetailGet> {
            override fun onResponse(call: Call<ResReportDetailGet>, response: Response<ResReportDetailGet>) {
                if (response.isSuccessful) {
                    _reportDetailList.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResReportDetailGet>, t: Throwable) {
                Log.e(TAG, "fail->" + "getReportDetails", t)
            }
        })
    }

    fun setStartEnd(start: Long, end: Long) {
        _startEnd.value = listOf(start, end)
    }

    fun getReview(start: Long, end: Long) {
        reviewRepo.getReviews(start, end).enqueue(object : Callback<ResReviewGet> {
            override fun onResponse(call: Call<ResReviewGet>, response: Response<ResReviewGet>) {
                if (response.isSuccessful) {
                    _reviewList.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResReviewGet>, t: Throwable) {
                Log.e(TAG, "$start ->" + "getReport", t)
            }
        })
    }

    fun postReview(body: ReqReviewAdd) {
        reviewRepo.postReviews(body).enqueue(object : Callback<ResReviewAdd> {
            override fun onResponse(call: Call<ResReviewAdd>, response: Response<ResReviewAdd>) {
                if (response.isSuccessful) {
                    Log.i(TAG, response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResReviewAdd>, t: Throwable) {
                Log.e(TAG, "putReview", t)
            }
        })
    }

    companion object{
        private val TAG=RemindViewModel::class.java.simpleName
    }
}