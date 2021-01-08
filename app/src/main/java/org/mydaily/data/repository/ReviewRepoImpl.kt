package org.mydaily.data.repository

import org.mydaily.data.model.network.response.ResReviewGet
import org.mydaily.data.remote.datasource.ReviewRemoteDataSource
import retrofit2.Call

class ReviewRepoImpl(private val remoteDataSource: ReviewRemoteDataSource) : ReviewRepo {
    override fun getReviews(jwt: String, start: String, end: String): Call<ResReviewGet> =
        remoteDataSource.getReviews(jwt, start, end)

    override fun postReviews(jwt: String, start: String, end: String) =
        remoteDataSource.postReviews(jwt, start, end)
}