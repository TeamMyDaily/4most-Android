package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqReviewAdd(
    @SerializedName("start")
    val start: Long,
    @SerializedName("end")
    val end: Long,
    @SerializedName("now")
    val now: Long,
    @SerializedName("subType")
    val subType: Int,
    @SerializedName("content")
    val content: String
)