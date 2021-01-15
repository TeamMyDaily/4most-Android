package org.mydaily.data.repository

import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.data.model.network.response.ResReviewAdd
import org.mydaily.data.model.network.response.ResReviewGet
import retrofit2.Call

interface ReviewRepo {
    fun getReviews(start: Long, end: Long): Call<ResReviewGet>
    fun postReviews(body: ReqReviewAdd): Call<ResReviewAdd>
}