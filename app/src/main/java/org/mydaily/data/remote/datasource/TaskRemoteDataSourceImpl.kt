package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.api.TaskService
import retrofit2.Call

class TaskRemoteDataSourceImpl(private val service: TaskService) : TaskRemoteDataSource {
    override fun getTasks(date: Long): Call<ResTaskGet> =
        service.getTasks(date)

    override fun postTasks(body: ReqTaskAdd): Call<ResTaskAdd> =
        service.postTasks(body)

    override fun getTaskById(id: Int): Call<ResTaskDetail> =
        service.getTaskById(id)

    override fun putTask(id: Int, body: ReqTaskPut): Call<Response> =
        service.putTask(id, body)

    override fun deleteTask(id: Int): Call<Response> = service.deleteTask(id)
}