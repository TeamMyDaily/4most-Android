package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.datasource.GoalRemoteDataSource
import retrofit2.Call

class GoalRepoImpl(private val remoteDataSource: GoalRemoteDataSource) : GoalRepo {
    override fun getGoals(jwt: String, start: String, end: String): Call<ResGoalGet> =
        remoteDataSource.getGoals(jwt, start, end)

    override fun postGoals(jwt: String, body: ReqGoalPost): Call<ResGoalPost> =
        remoteDataSource.postGoals(jwt, body)

    override fun putGoals(jwt: String, id: Int, body: ReqGoalPut): Call<Response> =
        remoteDataSource.putGoals(jwt, id, body)

    override fun putGoalsCompletion(jwt: String, id: Int): Call<Response> =
        remoteDataSource.putGoalsCompletion(jwt, id)

    override fun deleteGoal(jwt: String, id: Int): Call<Response> =
        remoteDataSource.deleteGoal(jwt, id)

}