package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqReviewAdd(
    @SerializedName("start")
    val start: Long,
    @SerializedName("end")
    val end: Long,
    @SerializedName("now")
    val now: Long,
    @SerializedName("good")
    val good: String,
    @SerializedName("bad")
    val bad: String,
    @SerializedName("next")
    val next: String
)