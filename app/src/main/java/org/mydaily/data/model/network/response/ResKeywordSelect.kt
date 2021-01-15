package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResKeywordSelect(
    @SerializedName("data")
    val `data`: List<Data>,
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
        @SerializedName("id")
        val id: Int,
        @SerializedName("TotalKeywordId")
        val totalKeywordId: Int
    )
}