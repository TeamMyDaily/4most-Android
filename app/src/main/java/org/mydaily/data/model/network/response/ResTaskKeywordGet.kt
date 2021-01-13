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
        @SerializedName("userName")
        val userName: String,
        @SerializedName("keywords")
        val keywords: List<String>
    )
}