package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResGoalPost(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("date")
        val date: String,
        @SerializedName("goal")
        val goal: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("isGoalCompleted")
        val isGoalCompleted: Boolean,
        @SerializedName("TotalKeywordId")
        val totalKeywordId: String
    )
}