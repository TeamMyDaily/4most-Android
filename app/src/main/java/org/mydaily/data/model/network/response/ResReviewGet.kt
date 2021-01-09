package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResReviewGet(
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
        @SerializedName("isWritten")
        val isWritten: Boolean,
        @SerializedName("review")
        val review: Review
    ) {
        data class Review(
            @SerializedName("bad")
            val bad: String,
            @SerializedName("date")
            val date: String,
            @SerializedName("good")
            val good: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("next")
            val next: String,
            @SerializedName("UserId")
            val userId: Int
        )
    }
}