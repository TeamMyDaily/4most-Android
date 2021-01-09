package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqGoalPost(
    @SerializedName("startDate")
    val startDate: Long,
    @SerializedName("totalKeywordId")
    val totalKeywordId: String,
    @SerializedName("goal")
    val goal: String
)