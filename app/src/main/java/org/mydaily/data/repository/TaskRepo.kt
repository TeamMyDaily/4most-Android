package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import retrofit2.Call

interface TaskRepo {
    fun getTasks(jwt: String, date: Long): Call<ResTaskGet>
    fun postTasks(jwt: String, body: ReqTaskAdd): Call<ResTaskAdd>
    fun getTaskById(jwt: String, id: Int): Call<ResTaskDetail>
    fun putTask(jwt: String, id: Int, body: ReqTaskPut): Call<Response>
    fun deleteTask(jwt: String, id: Int): Call<Response>
}