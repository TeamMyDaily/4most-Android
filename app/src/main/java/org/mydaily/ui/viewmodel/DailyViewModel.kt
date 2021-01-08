package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.Keyword
import org.mydaily.ui.base.BaseViewModel

class DailyViewModel: BaseViewModel() {

    private val _keywordList = MutableLiveData<Keyword>()
    val keywordList : LiveData<Keyword>
        get() = _keywordList

    fun getKeywordData() {
        /* 임시 데이터 */
        val tempList = listOf(
            "IT 기술에 관한 아티클 정리하기",
            "글감 수집하기",
            "아티클 5개 이상 읽기",
            "브런치 개설 하기"
        )
        _keywordList.value = Keyword("아웃풋", tempList)
    }

}