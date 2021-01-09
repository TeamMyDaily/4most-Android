package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResTaskGet(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("TotalKeywordId")
        val totalKeywordId: Int,
        @SerializedName("priority")
        val priority: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("tasks")
        val tasks: List<Task>
    ){
        data class Task(
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }
}