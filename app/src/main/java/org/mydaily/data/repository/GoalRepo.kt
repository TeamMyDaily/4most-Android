package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import retrofit2.Call

interface GoalRepo {
    fun getGoals(start: Long, end: Long): Call<ResGoalGet>
    fun postGoals(body: ReqGoalPost): Call<ResGoalPost>
    fun putGoals(id: Int, body: ReqGoalPut): Call<Response>
    fun putGoalsCompletion(id: Int): Call<Response>
    fun deleteGoal(id: Int): Call<Response>
}