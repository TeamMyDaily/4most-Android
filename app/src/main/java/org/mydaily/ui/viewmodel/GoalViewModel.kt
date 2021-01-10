package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.domain.Goal
import org.mydaily.data.repository.GoalRepo
import org.mydaily.ui.base.BaseViewModel

class GoalViewModel(private val repo: GoalRepo) : BaseViewModel() {

    private val _notSetGoalCount = MutableLiveData<Int>()
    val notSetGoalCount: LiveData<Int>
        get() = _notSetGoalCount

    private val _goalList = MutableLiveData<List<Goal>>()
    val goalList: LiveData<List<Goal>>
        get() = _goalList

    fun getGoalData() {
        /* 임시 데이터 */
        val tempList = listOf(
            Goal(1, 1, "아웃풋", 1, "블로그에 1개이상 퍼블리싱 하기", true, true),
            Goal(2, 2, "열정", 2, "유노유노 영상 1개 이상 시청하기", true, false),
            Goal(3, 3, "선한영향력", 3, "거짓말 치지 않고 선하게 살기", true, true),
            Goal(4, 4, "건강", 4, "", false, false)
        )
        _goalList.value = tempList
        _notSetGoalCount.value = 1
    }

    fun putGoalsCompletion(goalId: Int) {
/*        repo.putGoalsCompletion(goalId).enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
            }

        })*/
    }

    fun postGoals(startDate: Long, totalKeywordId: String, goal: String) {
/*        repo.postGoals(ReqGoalPost(startDate, totalKeywordId, goal)).enqueue(object : Callback<ResGoalPost>{

        })*/
    }

    fun putGoals(goalId: Int, goal: String) {
//        repo.putGoals(goalId, ReqGoalPut(goal))
    }

    fun deleteGoal(goalId: Int) {
//        repo.deleteGoal(goalId)
    }
}