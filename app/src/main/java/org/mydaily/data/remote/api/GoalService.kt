package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import retrofit2.Call
import retrofit2.http.*

interface GoalService {
    //GET	주차별 목표 조회
    @GET("/goals")
    @Headers("Content-Type: application/json")
    fun getGoals(
        @Query("start") start: Long,
        @Query("end") end: Long
    ): Call<ResGoalGet>

    //POST	주차별 목표 등록
    @POST("/goals")
    @Headers("Content-Type: application/json")
    fun postGoals(
        @Body body: ReqGoalPost
    ): Call<ResGoalPost>

    //PUT	주차별 목표 수정
    @PUT("/goals/{goalId}")
    @Headers("Content-Type: application/json")
    fun putGoals(
        @Path("goalId") id: Int,
        @Body body: ReqGoalPut
    ): Call<Response>

    //PUT	주차별 목표 달성여부 변경
    @PUT("/goals/completion/{goalId}")
    @Headers("Content-Type: application/json")
    fun putGoalsCompletion(
        @Path("goalId") id: Int
    ): Call<Response>

    //DELETE	주차별 목표 삭제
    @DELETE("/goals/{goalId}")
    @Headers("Content-Type: application/json")
    fun deleteGoal(
        @Path("goalId") id: Int
    ): Call<Response>
}