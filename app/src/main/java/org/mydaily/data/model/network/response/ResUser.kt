package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResUser(
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
        @SerializedName("keywords")
        val keywords: List<String>,
        @SerializedName("userName")
        val userName: String
    )
}