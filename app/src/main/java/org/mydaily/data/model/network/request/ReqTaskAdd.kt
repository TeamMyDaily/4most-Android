package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqTaskAdd(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("satisfaction")
    val satisfaction: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("totalKeywordId")
    val totalKeywordId: String
)