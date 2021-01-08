package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.request.ReqTaskAdd
import org.mydaily.data.model.network.request.ReqTaskPut
import org.mydaily.data.model.network.response.ResTaskAdd
import org.mydaily.data.model.network.response.ResTaskDetail
import org.mydaily.data.model.network.response.ResTaskGet
import org.mydaily.data.model.network.response.Response
import retrofit2.Call
import retrofit2.http.*

interface TaskService {
    //GET	나의 기록 조회(메인)
    @GET("/tasks")
    @Headers("Content-Type: application/json")
    fun getTasks(
        @Query("date") date: Long
    ): Call<ResTaskGet>

    //GET	나의 기록 개별 조회
    @GET("/tasks/{taskId}")
    @Headers("Content-Type: application/json")
    fun getTaskById(
        @Path("taskId") id: Int
    ): Call<ResTaskDetail>

    //POST	나의 기록 추가
    @POST("/tasks")
    @Headers("Content-Type: application/json")
    fun postTasks(
        @Body body: ReqTaskAdd
    ): Call<ResTaskAdd>

    //PUT	나의 기록 수정
    @PUT("/tasks/{taskId}")
    @Headers("Content-Type: application/json")
    fun putTask(
        @Path("taskId") id: Int,
        @Body body: ReqTaskPut
    ): Call<Response>

    //DELETE	나의 기록 삭제
    @DELETE("/tasks/{taskId}")
    @Headers("Content-Type: application/json")
    fun deleteTask(
        @Path("taskId") id: Int
    ): Call<Response>
}