package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.Goal
import org.mydaily.ui.base.BaseViewModel

class GoalViewModel : BaseViewModel() {

    private val _goalList = MutableLiveData<List<Goal>>()
    val goalList: LiveData<List<Goal>>
        get() = _goalList

    fun getGoalData() {
        /* 임시 데이터 */
        val tempList = listOf(
            Goal(1, "아웃풋", "블로그에 1개 이상 퍼블리싱 하기", isGoalExist = true, isGoalCompleted = true),
            Goal(2, "열정", "", isGoalExist = false, isGoalCompleted = false),
            Goal(3, "경청", "", isGoalExist = false, isGoalCompleted = false),
            Goal(4, "선한영향력", "거짓말 치지 않고 선하게 살기", true, isGoalCompleted = false)
        )
        _goalList.value = tempList
    }
}