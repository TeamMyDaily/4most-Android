package org.mydaily.data.remote.datasource

import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.data.model.network.response.ResReviewAdd
import org.mydaily.data.model.network.response.ResReviewGet
import retrofit2.Call

interface ReviewRemoteDataSource {
    fun getReviews(jwt: String, start: String, end: String): Call<ResReviewGet>

    fun postReviews(jwt: String, body: ReqReviewAdd): Call<ResReviewAdd>
}