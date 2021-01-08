package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqGoalPost(
    @SerializedName("goal")
    val goal: String,
    @SerializedName("totalKeywordId")
    val totalKeywordId: String
)