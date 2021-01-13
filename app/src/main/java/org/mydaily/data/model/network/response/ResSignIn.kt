package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResSignIn(
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
        @SerializedName("email")
        val email: String,
        @SerializedName("accessToken")
        val accessToken: String,
        @SerializedName("refreshToken")
        val refreshToken: String
    )
}