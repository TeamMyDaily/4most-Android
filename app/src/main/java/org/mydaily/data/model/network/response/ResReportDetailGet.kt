package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResReportDetailGet(
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
        @SerializedName("totalKeywordId")
        val totalKeywordId: Int,
        @SerializedName("weekGoalId")
        val weekGoalId: Int,
        @SerializedName("goal")
        val goal: String,
        @SerializedName("isGoalCompleted")
        val isGoalCompleted: Boolean,
        @SerializedName("keywordName")
        val keywordName: String,
        @SerializedName("tasks")
        val tasks: List<Task>
    ) {
        data class Task(
            @SerializedName("date")
            val date: String,
            @SerializedName("satisfaction")
            val satisfaction: Int,
            @SerializedName("taskId")
            val taskId: Int,
            @SerializedName("title")
            val title: String
        )
    }
}