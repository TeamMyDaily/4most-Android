package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.Keyword
import org.mydaily.data.model.domain.Task
import org.mydaily.ui.base.BaseViewModel

class DailyViewModel: BaseViewModel() {

    private val _keywordList = MutableLiveData<List<Task>>()
    val keywordList : LiveData<List<Task>>
        get() = _keywordList

    fun getKeywordData() {
        /* 임시 데이터 */
        val tempList = listOf(
            Task(1,"아웃풋1",1, listOf("IT 기술에 관한 아티클 정리하기","글감 수집하기")),
            Task(2, "아웃풋2",2, listOf("브런치 개설 하기")),
            Task(3, "아웃풋3",3, listOf("글감 수집하기","아티클 5개 이상 읽기","브런치 개설 하기")),
            Task(4, "아웃풋4",4, listOf("IT 기술에 관한 아티클 정리하기","글감 수집하기","아티클 5개 이상 읽기","브런치 개설 하기")),
        )
        _keywordList.value = tempList
    }

}