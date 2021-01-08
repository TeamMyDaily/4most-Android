package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.api.GoalService
import retrofit2.Call

class GoalRemoteDataSourceImpl(private val service: GoalService) : GoalRemoteDataSource {
    override fun getGoals(start: String, end: String): Call<ResGoalGet> =
        service.getGoals(start, end)

    override fun postGoals(body: ReqGoalPost): Call<ResGoalPost> =
        service.postGoals(body)

    override fun putGoals(id: Int, body: ReqGoalPut): Call<Response> =
        service.putGoals(id, body)

    override fun putGoalsCompletion(id: Int): Call<Response> =
        service.putGoalsCompletion(id)

    override fun deleteGoal(id: Int): Call<Response> =
        service.deleteGoal(id)
}