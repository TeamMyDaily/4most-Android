package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResGoalGet(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("notSetGoalCount")
        val notSetGoalCount: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("keywords")
        val keywords: List<Keyword>
    ) {
        data class Keyword(
            @SerializedName("totalKeywordId")
            val totalKeywordId: Int,
            @SerializedName("priority")
            val priority: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("isGoalCreated")
            val isGoalCreated: Boolean,
            @SerializedName("weekGoalId")
            val weekGoalId: Int,
            @SerializedName("weekGoal")
            val weekGoal: String,
            @SerializedName("isGoalCompleted")
            val isGoalCompleted: Boolean
        )
    }
}