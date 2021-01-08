package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.api.TaskService
import retrofit2.Call

class TaskRemoteDataSourceImpl(private val service: TaskService) : TaskRemoteDataSource {
    override fun getTasks(jwt: String, date: Long): Call<ResTaskGet> =
        service.getTasks(jwt, date)

    override fun postTasks(jwt: String, body: ReqTaskAdd): Call<ResTaskAdd> =
        service.postTasks(jwt, body)

    override fun getTaskById(jwt: String, id: Int): Call<ResTaskDetail> =
        service.getTaskById(jwt, id)

    override fun putTask(jwt: String, id: Int): Call<Response> = service.putTask(jwt, id)

    override fun deleteTask(jwt: String, id: Int): Call<Response> = service.deleteTask(jwt, id)
}