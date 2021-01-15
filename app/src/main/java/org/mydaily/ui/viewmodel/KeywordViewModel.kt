package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqKeywordDefine
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.data.model.network.request.ReqKeywordSelect
import org.mydaily.data.model.network.response.ResKeywordDefinitionGet
import org.mydaily.data.model.network.response.ResKeywordListGet
import org.mydaily.data.model.network.response.ResKeywordSelect
import org.mydaily.data.model.network.response.ResTaskKeywordGet
import org.mydaily.data.repository.KeywordRepo
import org.mydaily.ui.base.BaseViewModel
import org.mydaily.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeywordViewModel(private val repo: KeywordRepo) : BaseViewModel() {

    private val _lifeWordList = MutableLiveData<List<String>>()
    val lifeWordList: LiveData<List<String>>
        get() = _lifeWordList

    private val _workWordList = MutableLiveData<List<String>>()
    val workWordList: LiveData<List<String>>
        get() = _workWordList

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    private val _taskKeywordList = MutableLiveData<List<ResTaskKeywordGet.Data.Keyword>>()
    val taskKeywordList: LiveData<List<ResTaskKeywordGet.Data.Keyword>>
        get() = _taskKeywordList

    private val _keywordList = MutableLiveData<List<ResKeywordListGet.Data>>()
    val keywordList: LiveData<List<ResKeywordListGet.Data>>
        get() = _keywordList

    //지울예정
    private val _keywordStringList = MutableLiveData<List<String>>()
    val keywordStringList: LiveData<List<String>>
        get() = _keywordStringList

    private val _keywordDefinition = MutableLiveData<ResKeywordDefinitionGet.Data>()
    val keywordDefinition: LiveData<ResKeywordDefinitionGet.Data>
        get() = _keywordDefinition

    var isDefineSet = arrayOf(false,false,false,false)
    var selectedKeywordIds = mutableListOf<Int>()

    fun getLifeWord() {
        val tempList = listOf(
            "신뢰", "행복", "배려", "다양성", "감사", "인내", "경험", "용서", "정의", "긍정", "건강", "자유", "나눔", "자신감", "도전", "풍요로움",
            "양심", "부", "정직", "변화"
        )
        _lifeWordList.value = tempList
    }

    fun getWorkWord() {
        val tempList = listOf(
            "몰입", "열정", "배움", "결과", "과정", "소통", "효율성", "성취", "인정", "보람", "성장", "탁월함", "혁신", "협력", "성실", "책임", "본질", "완벽", "실천", "목적의식"
        )
        _workWordList.value = tempList
    }

    fun postKeywordSelect(keywords: List<String>) {
        repo.postKeywordSelect(ReqKeywordSelect(keywords = keywords))
            .enqueue(object : Callback<ResKeywordSelect> {

                override fun onResponse(
                    call: Call<ResKeywordSelect>,
                    response: retrofit2.Response<ResKeywordSelect>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                    } else {
                        _toastMessage.postValue(Event("응답 실패"))
                    }
                }

                override fun onFailure(call: Call<ResKeywordSelect>, t: Throwable) {
                    _toastMessage.postValue(Event("응답 실패"))
                }
            })
    }

    fun postKeywordPriority(list: List<ReqKeywordPriority.Keyword>) {
        repo.postKeywordPriority(ReqKeywordPriority(list))
            .enqueue(object : Callback<org.mydaily.data.model.network.response.Response>{
                override fun onResponse(
                    call: Call<org.mydaily.data.model.network.response.Response>,
                    response: Response<org.mydaily.data.model.network.response.Response>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                    }
                }

                override fun onFailure(
                    call: Call<org.mydaily.data.model.network.response.Response>,
                    t: Throwable
                ) {
                    Log.e(TAG, "postKeywordPriority", t)
                }
            }
        )
    }

    /*마이페이지*/
    fun getTaskKeyword() {
        repo.getTaskKeyword()
            .enqueue(object : Callback<ResTaskKeywordGet> {
                override fun onResponse(
                    call: Call<ResTaskKeywordGet>,
                    response: Response<ResTaskKeywordGet>
                ) {
                    if (response.isSuccessful) {
                        _taskKeywordList.value = response.body()?.data?.keywords

                        //매핑
                        val keywords = mutableListOf<String>()
                        for(data in response.body()?.data?.keywords!!){
                            keywords.add(data.name)
                            selectedKeywordIds.add(data.totalKeywordId)
                        }
                        Log.e(TAG, selectedKeywordIds.toString())
                        _keywordStringList.value = keywords
                    }
                }

                override fun onFailure(call: Call<ResTaskKeywordGet>, t: Throwable) {
                    Log.e(TAG, "getTaskKeyword", t)
                }
            })
    }

    fun getKeywordList() {
        repo.getKeywordList().enqueue(object : Callback<ResKeywordListGet> {
            override fun onResponse(
                call: Call<ResKeywordListGet>,
                response: Response<ResKeywordListGet>
            ) {
                if (response.isSuccessful) {
                    _keywordList.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<ResKeywordListGet>, t: Throwable) {
                Log.e(TAG, "getKeywordList", t)
            }

        })
    }

    fun postKeywordDefinition(name:String, definition:String) {
        repo.postKeywordDefinition(ReqKeywordDefine(name, definition))
            .enqueue(object : Callback<org.mydaily.data.model.network.response.Response>{
            override fun onResponse(
                call: Call<org.mydaily.data.model.network.response.Response>,
                response: Response<org.mydaily.data.model.network.response.Response>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, response.body().toString())
                }
            }

            override fun onFailure(
                call: Call<org.mydaily.data.model.network.response.Response>,
                t: Throwable
            ) {
                Log.e(TAG,"postKeywordDefinition", t)
            }

        })
    }

    fun getKeywordDefinition(id: Int){
        repo.getKeywordDefinition(id)
            .enqueue(object : Callback<ResKeywordDefinitionGet>{
                override fun onResponse(
                    call: Call<ResKeywordDefinitionGet>,
                    response: Response<ResKeywordDefinitionGet>
                ) {
                    if (response.isSuccessful) {
                        _keywordDefinition.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<ResKeywordDefinitionGet>, t: Throwable) {
                    Log.e(TAG,"getKeywordDefinition", t)
                }

            })
    }

    companion object {
        private val TAG = KeywordViewModel::class.java.simpleName
    }
}