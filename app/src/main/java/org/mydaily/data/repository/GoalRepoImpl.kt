package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqGoalPost
import org.mydaily.data.model.network.request.ReqGoalPut
import org.mydaily.data.model.network.response.ResGoalGet
import org.mydaily.data.model.network.response.ResGoalPost
import org.mydaily.data.model.network.response.Response
import org.mydaily.data.remote.datasource.GoalRemoteDataSource
import retrofit2.Call

class GoalRepoImpl(private val remoteDataSource: GoalRemoteDataSource) : GoalRepo {
    override fun getGoals(start: String, end: String): Call<ResGoalGet> =
        remoteDataSource.getGoals(start, end)

    override fun postGoals(body: ReqGoalPost): Call<ResGoalPost> =
        remoteDataSource.postGoals(body)

    override fun putGoals(id: Int, body: ReqGoalPut): Call<Response> =
        remoteDataSource.putGoals(id, body)

    override fun putGoalsCompletion(id: Int): Call<Response> =
        remoteDataSource.putGoalsCompletion(id)

    override fun deleteGoal(id: Int): Call<Response> =
        remoteDataSource.deleteGoal(id)

}