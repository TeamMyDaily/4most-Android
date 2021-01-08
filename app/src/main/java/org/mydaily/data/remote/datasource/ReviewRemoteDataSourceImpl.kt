package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.response.ResReviewGet
import org.mydaily.data.remote.api.ReviewService
import retrofit2.Call

class ReviewRemoteDataSourceImpl(private val service: ReviewService) : ReviewRemoteDataSource {
    override fun getReviews(jwt: String, start: String, end: String): Call<ResReviewGet> =
        service.getReviews(jwt, start, end)

    override fun postReviews(jwt: String, start: String, end: String) =
        service.postReviews(jwt, start, end)
}