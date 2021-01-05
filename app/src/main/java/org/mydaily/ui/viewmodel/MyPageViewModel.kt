package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.ui.base.BaseViewModel

class MyPageViewModel : BaseViewModel() {
    private val _keywordList = MutableLiveData<List<String>>()
    val keywordList: LiveData<List<String>>
        get() = _keywordList

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun getUser() {
        /* 임시 데이터 */
        _userName.value = "김슬기"
    }

    fun getKeywordData() {
        /* 임시 데이터 */
        val tempList = listOf(
            "아웃풋", "열정", "경청", "선한영향력"
        )
        _keywordList.value = tempList
    }
}