package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResReviewAdd(
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
        @SerializedName("good")
        val good: String,
        @SerializedName("bad")
        val bad: String,
        @SerializedName("next")
        val next: String
    )
}