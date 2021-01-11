package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName
import org.mydaily.data.model.domain.Task
import org.mydaily.data.model.domain.TaskDetail
import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.repository.TaskRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyViewModel(private val repo: TaskRepo) : BaseViewModel() {

    private val _keywordList = MutableLiveData<List<ResTaskGet.Data>>()
    val keywordList: LiveData<List<ResTaskGet.Data>>
        get() = _keywordList

    private val _task = MutableLiveData<TaskDetail>()
    val task: LiveData<TaskDetail>
        get() = _task

/*    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>>
        get() = _taskList*/

/*    fun getTask(date: Long) {
        repo.getTasks(date).enqueue(object : Callback<ResTaskGet> {
            override fun onResponse(call: Call<ResTaskGet>, response: Response<ResTaskGet>) {
            }

            override fun onFailure(call: Call<ResTaskGet>, t: Throwable) {
            }

        })
    }*/

    fun postTask(totalKeywordId: String, title: String, detail: String, satisfaction: Int) {
        Log.e("DailyViewModel", ""+ReqTaskAdd(totalKeywordId, title, detail, satisfaction))
/*        repo.postTasks(ReqTaskAdd(totalKeywordId, title, detail, satisfaction))
            .enqueue(object :Callback<ResTaskAdd>{
                override fun onResponse(call: Call<ResTaskAdd>, response: Response<ResTaskAdd>) {
                }

                override fun onFailure(call: Call<ResTaskAdd>, t: Throwable) {
                }
            })*/
    }

    fun getTaskById(taskId: Int) {
        /* 임시 데이터 */
        _task.value =
            TaskDetail(
                1, 1, "IT 기술에 관한 아티클 정리하기", "드라마 스타트업 따라잡기라는 글을 보고 AI의 미래에 대한 간단한 고찰을 할 수 있었다.\n" +
                        "이 글을 보고 AI 관련 글을 더 찾아보고 정리했다.", 3
            )

    }

    fun getTasks(date: Long) {
        repo.getTasks(date).enqueue(object : Callback<ResTaskGet>{
            override fun onResponse(call: Call<ResTaskGet>, response: Response<ResTaskGet>) {
                if(response.isSuccessful){
                    _keywordList.postValue(response.body()?.data)
                }
            }
            override fun onFailure(call: Call<ResTaskGet>, t: Throwable) {
                Log.e(TAG, "getTasks", t)
            }
        })
    }

    companion object{
        private val TAG = DailyViewModel::class.java.simpleName
    }
}