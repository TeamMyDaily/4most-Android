package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.datasource.TaskRemoteDataSource
import retrofit2.Call

class TaskRepoImpl(private val remoteDataSource: TaskRemoteDataSource) : TaskRepo {
    override fun getTasks(date: Long): Call<ResTaskGet> =
        remoteDataSource.getTasks(date)

    override fun postTasks(body: ReqTaskAdd): Call<ResTaskAdd> =
        remoteDataSource.postTasks(body)

    override fun getTaskById(id: Int): Call<ResTaskDetail> =
        remoteDataSource.getTaskById(id)

    override fun putTask(id: Int, body: ReqTaskPut): Call<Response> =
        remoteDataSource.putTask(id, body)

    override fun deleteTask(id: Int): Call<Response> =
        remoteDataSource.deleteTask(id)
}