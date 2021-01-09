package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResGoalGet(
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
        @SerializedName("count")
        val count: Int,
        @SerializedName("keywords")
        val keywords: List<Keyword>
    ) {
        data class Keyword(
            @SerializedName("isGoalCompleted")
            val isGoalCompleted: Boolean,
            @SerializedName("isGoalCreated")
            val isGoalCreated: Boolean,
            @SerializedName("name")
            val name: String,
            @SerializedName("priority")
            val priority: Int,
            @SerializedName("totalKeywordId")
            val totalKeywordId: Int,
            @SerializedName("weekGoal")
            val weekGoal: String,
            @SerializedName("weekGoalId")
            val weekGoalId: Int
        )
    }
}