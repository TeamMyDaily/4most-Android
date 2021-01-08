package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import retrofit2.Call

interface GoalRemoteDataSource {
    fun getGoals(jwt: String, start: String, end: String): Call<ResGoalGet>
    fun postGoals(jwt: String, body: ReqGoalPost): Call<ResGoalPost>
    fun putGoals(jwt: String, id: Int, body: ReqGoalPut): Call<Response>
    fun putGoalsCompletion(jwt: String, id: Int): Call<Response>
    fun deleteGoal(jwt: String, id: Int): Call<Response>
}