package org.mydaily.data.remote.api

import org.mydaily.data.model.network.request.ReqReviewAdd
import org.mydaily.data.model.network.response.ResReviewAdd
import org.mydaily.data.model.network.response.ResReviewGet
import retrofit2.Call
import retrofit2.http.*

interface ReviewService {
    //GET	주차별 회고 조회
    @GET("/reviews")
    @Headers("Content-Type: application/json")
    fun getReviews(
        @Query("start") start: String,
        @Query("end") end: String
    ): Call<ResReviewGet>

    //POST	주차별 회고 생성
    @POST("/reviews")
    @Headers("Content-Type: application/json")
    fun postReviews(
        @Body body: ReqReviewAdd
    ): Call<ResReviewAdd>
}