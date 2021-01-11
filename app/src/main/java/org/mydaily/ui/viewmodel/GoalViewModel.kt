package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.repository.GoalRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback

class GoalViewModel(private val repo: GoalRepo) : BaseViewModel() {

    private val _notSetGoalCount = MutableLiveData<Int>()
    val notSetGoalCount: LiveData<Int>
        get() = _notSetGoalCount

    private val _goalList = MutableLiveData<List<ResGoalGet.Data.Keyword>>()
    val goalList: LiveData<List<ResGoalGet.Data.Keyword>>
        get() = _goalList
/*
    fun getGoalData() {
        *//* 임시 데이터 *//*
        val tempList = listOf(
            Goal(1, 1, "아웃풋", 1, "블로그에 1개이상 퍼블리싱 하기", true, true),
            Goal(2, 2, "열정", 2, "유노유노 영상 1개 이상 시청하기", true, false),
            Goal(3, 3, "선한영향력", 3, "거짓말 치지 않고 선하게 살기", true, true),
            Goal(4, 4, "건강", 4, "", false, false)
        )
        _goalList.value = tempList
        _notSetGoalCount.value = 1
    }*/


    fun getGoals(start: Long, end: Long) {
        repo.getGoals(start, end).enqueue(object : Callback<ResGoalGet>{
            override fun onResponse(
                call: Call<ResGoalGet>,
                response: retrofit2.Response<ResGoalGet>
            ) {
                if(response.isSuccessful){
                    _goalList.postValue(response.body()?.data?.keywords)
                    _notSetGoalCount.postValue(response.body()?.data?.notSetGoalCount)
                }

            }
            override fun onFailure(call: Call<ResGoalGet>, t: Throwable) {

            }
        })
    }

    fun putGoalsCompletion(goalId: Int) {
        repo.putGoalsCompletion(goalId).enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if(response.isSuccessful){
                    Log.i(TAG, response.body().toString())
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e(TAG, "putGoalsCompletion", t)
            }

        })
    }

    fun postGoals(startDate: Long, totalKeywordId: String, goal: String) {
        repo.postGoals(ReqGoalPost(startDate, totalKeywordId, goal))
            .enqueue(object : Callback<ResGoalPost>{
                override fun onResponse(
                    call: Call<ResGoalPost>,
                    response: retrofit2.Response<ResGoalPost>
                ) {
                    if(response.isSuccessful){
                        Log.i(TAG, response.body().toString())
                    }
                }
                override fun onFailure(call: Call<ResGoalPost>, t: Throwable) {
                    Log.e(TAG, "postGoals", t)
                }
            })
    }

    fun putGoals(goalId: Int, goal: String) {
//        repo.putGoals(goalId, ReqGoalPut(goal))
    }

    fun deleteGoal(goalId: Int) {
//        repo.deleteGoal(goalId)
    }


    companion object{
        private val TAG = GoalViewModel::class.java.simpleName
    }
}