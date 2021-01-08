package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.data.model.network.response.ResReviewAdd
import org.mydaily.data.model.network.response.ResReviewGet
import org.mydaily.data.remote.api.ReviewService
import retrofit2.Call

class ReviewRemoteDataSourceImpl(private val service: ReviewService) : ReviewRemoteDataSource {
    override fun getReviews(start: String, end: String): Call<ResReviewGet> =
        service.getReviews(start, end)

    override fun postReviews(body: ReqReviewAdd): Call<ResReviewAdd> =
        service.postReviews(body)
}