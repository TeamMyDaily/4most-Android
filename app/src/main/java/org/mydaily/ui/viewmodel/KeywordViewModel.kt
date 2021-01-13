package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqKeywordDefine
import org.mydaily.data.model.network.request.ReqKeywordPriority
import org.mydaily.data.model.network.request.ReqKeywordSelect
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

    fun getLifeWord() {
        val tempList = listOf(
            "진정성", "용기", "열정", "꾸준함", "배움", "선한영향력", "아웃풋", "행복", "즐거움", "현명", "타당성", "정당성"
        )
        _lifeWordList.value = tempList
    }

    fun getWorkWord() {
        val tempList = listOf(
            "친절함", "경청", "대충", "진실성", "존중", "신뢰", "의심", "신속성", "돈"
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

/*                        //매핑
                        val keywords = mutableListOf<String>()
                        for(data in response.body()?.data?.keywords!!){
                            keywords.add(data.name)
                        }
                        _keywordStringList.value = keywords*/

                        Log.e(TAG+"hi", response.body().toString())
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

    companion object {
        private val TAG = KeywordViewModel::class.java.simpleName
    }
}