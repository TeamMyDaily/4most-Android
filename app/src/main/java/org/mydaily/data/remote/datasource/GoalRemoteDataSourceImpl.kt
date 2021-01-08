package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.api.GoalService
import retrofit2.Call

class GoalRemoteDataSourceImpl(private val service: GoalService) : GoalRemoteDataSource {
    override fun getGoals(jwt: String, start: String, end: String): Call<ResGoalGet> =
        service.getGoals(jwt, start, end)

    override fun postGoals(jwt: String, body: ReqGoalPost): Call<ResGoalPost> =
        service.postGoals(jwt, body)

    override fun putGoals(jwt: String, id: Int, body: ReqGoalPut): Call<Response> =
        service.putGoals(jwt, id, body)

    override fun putGoalsCompletion(jwt: String, id: Int): Call<Response> =
        service.putGoalsCompletion(jwt, id)

    override fun deleteGoal(jwt: String, id: Int): Call<Response> =
        service.deleteGoal(jwt, id)
}