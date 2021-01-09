package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResPassword(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)