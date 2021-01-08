package org.mydaily.data.repository

import org.mydaily.data.model.network.response.ResReviewGet
import retrofit2.Call

interface ReviewRepo {
    fun getReviews(jwt: String, start: String, end: String): Call<ResReviewGet>

    /* TODO : [POST] 주차별 회고 생성*/
    fun postReviews(jwt: String, start: String, end: String)
}