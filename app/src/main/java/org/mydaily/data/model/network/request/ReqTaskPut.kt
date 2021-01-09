package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqTaskPut(
    @SerializedName("title")
    val title: String,
    @SerializedName("detail")
    val detail: String,
    @SerializedName("satisfaction")
    val satisfaction: Int
)