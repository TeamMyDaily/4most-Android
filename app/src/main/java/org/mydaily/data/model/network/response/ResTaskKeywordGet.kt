package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResTaskKeywordGet(
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
        @SerializedName("keywords")
        val keywords: List<Keyword>
    ) {
        data class Keyword(
            @SerializedName("totalKeywordId")
            val totalKeywordId: Int,
            @SerializedName("name")
            val name: String
        )
    }
}