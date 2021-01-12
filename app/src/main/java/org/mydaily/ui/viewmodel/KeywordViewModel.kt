package org.mydaily.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.repository.KeywordRepo
import org.mydaily.ui.base.BaseViewModel

class KeywordViewModel(private val repo : KeywordRepo) : BaseViewModel() {

    private val _lifeWordList = MutableLiveData<List<String>>()
    val lifeWordList: LiveData<List<String>>
        get() = _lifeWordList

    private val _workWordList = MutableLiveData<List<String>>()
    val workWordList: LiveData<List<String>>
        get() = _workWordList


    //삶을 대하는 자세
    fun getLifeWord() {
        /* 임시 데이터 */
        val tempList = listOf(
            "진정성", "용기", "열정", "꾸준함", "배움", "선한영향력", "아웃풋", "행복", "즐거움", "현명", "타당성", "정당성"
        )
        _lifeWordList.value = tempList
    }

    //일을 대하는 자세
    fun getWorkWord() {
        /* 임시 데이터 */
        val tempList = listOf(
            "친절함", "경청", "대충", "진실성", "존중", "신뢰", "의심", "신속성", "돈"
        )
        _workWordList.value = tempList
    }

}