package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqPassword(
    @SerializedName("password")
    val password: String
)