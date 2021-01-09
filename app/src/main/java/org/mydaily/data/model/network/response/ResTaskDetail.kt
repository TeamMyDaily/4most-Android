package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResTaskDetail(
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
        @SerializedName("detail")
        val detail: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("satisfaction")
        val satisfaction: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("TotalKeywordId")
        val totalKeywordId: Int
    )
}