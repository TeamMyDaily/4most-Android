package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import retrofit2.Call

interface GoalRemoteDataSource {
    fun getGoals(start: String, end: String): Call<ResGoalGet>
    fun postGoals(body: ReqGoalPost): Call<ResGoalPost>
    fun putGoals(id: Int, body: ReqGoalPut): Call<Response>
    fun putGoalsCompletion(id: Int): Call<Response>
    fun deleteGoal(id: Int): Call<Response>
}