package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import retrofit2.Call

interface TaskRepo {
    fun getTasks(date: Long): Call<ResTaskGet>
    fun postTasks(body: ReqTaskAdd): Call<ResTaskAdd>
    fun getTaskById(id: Int): Call<ResTaskDetail>
    fun putTask(id: Int, body: ReqTaskPut): Call<Response>
    fun deleteTask(id: Int): Call<Response>
}