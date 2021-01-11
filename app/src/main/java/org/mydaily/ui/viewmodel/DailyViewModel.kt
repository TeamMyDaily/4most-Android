package org.mydaily.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.repository.TaskRepo
import org.mydaily.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyViewModel(private val repo: TaskRepo) : BaseViewModel() {

    private val _taskList = MutableLiveData<List<ResTaskGet.Data.Result>>()
    val taskList: LiveData<List<ResTaskGet.Data.Result>>
        get() = _taskList

    private val _task = MutableLiveData<ResTaskDetail.Data>()
    val task: LiveData<ResTaskDetail.Data>
        get() = _task

    fun getTasks(date: Long){
        repo.getTasks(date).enqueue(object : Callback<ResTaskGet>{
            override fun onResponse(call: Call<ResTaskGet>, response: Response<ResTaskGet>) {
                if(response.isSuccessful){
                    _taskList.postValue(response.body()?.data?.result)
                    Log.e(TAG, "$date ->"+response.body().toString())
                }
            }
            override fun onFailure(call: Call<ResTaskGet>, t: Throwable) {
                Log.e(TAG, "getTasks", t)
            }
        })
    }

    fun getTaskById(taskId: Int) {
        repo.getTaskById(taskId).enqueue(object : Callback<ResTaskDetail>{
            override fun onResponse(call: Call<ResTaskDetail>, response: Response<ResTaskDetail>) {
                if(response.isSuccessful){
                    _task.postValue(response.body()?.data)
                }
            }
            override fun onFailure(call: Call<ResTaskDetail>, t: Throwable) {
                Log.e(TAG, "getTaskById", t)
            }
        })
    }

    fun postTask(totalKeywordId: String, title: String, detail: String, satisfaction: Int) {
        repo.postTasks(ReqTaskAdd(totalKeywordId, title, detail, satisfaction))
            .enqueue(object :Callback<ResTaskAdd>{
                override fun onResponse(call: Call<ResTaskAdd>, response: Response<ResTaskAdd>) {
                    if(response.isSuccessful){
                        Log.e(TAG, response.body().toString())
                    }
                }
                override fun onFailure(call: Call<ResTaskAdd>, t: Throwable) {
                    Log.e(TAG, "postTask", t)
                }
            })
    }

    fun putTask(taskId: Int,  title: String, detail: String, satisfaction: Int){
        repo.putTask(taskId, ReqTaskPut(title, detail, satisfaction))
            .enqueue(object : Callback<org.mydaily.data.model.network.response.Response>{
                override fun onResponse(
                    call: Call<org.mydaily.data.model.network.response.Response>,
                    response: Response<org.mydaily.data.model.network.response.Response>
                ) {
                    Log.e(TAG, response.body().toString())
                }

                override fun onFailure(
                    call: Call<org.mydaily.data.model.network.response.Response>,
                    t: Throwable
                ) {
                    Log.e(TAG, "putTask", t)
                }
            })
    }

    fun deleteTask(taskId: Int) {
        repo.deleteTask(taskId).enqueue(object : Callback<org.mydaily.data.model.network.response.Response>{
            override fun onResponse(
                call: Call<org.mydaily.data.model.network.response.Response>,
                response: Response<org.mydaily.data.model.network.response.Response>
            ) {
                Log.e(TAG, "deleteTask : ${response.isSuccessful}")
            }

            override fun onFailure(
                call: Call<org.mydaily.data.model.network.response.Response>,
                t: Throwable
            ) {
                Log.e(TAG, "deleteTask", t)
            }

        })
    }

    companion object{
        private val TAG = DailyViewModel::class.java.simpleName
    }
}