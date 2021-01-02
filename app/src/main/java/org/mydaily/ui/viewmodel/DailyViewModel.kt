package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.Keyword
import org.mydaily.ui.base.BaseViewModel

class DailyViewModel: BaseViewModel() {

    private val _keywordList = MutableLiveData<List<Keyword>>()
    val keywordList : LiveData<List<Keyword>>
        get() = _keywordList

    fun getKeywordData() {
        /* 임시 데이터 */
        val tempList = listOf<Keyword>(
            Keyword("아웃풋","1"),
            Keyword("열정","2"),
            Keyword("선한영향력","3"),
            Keyword("진정성","4")
        )
        _keywordList.value = tempList
    }

}