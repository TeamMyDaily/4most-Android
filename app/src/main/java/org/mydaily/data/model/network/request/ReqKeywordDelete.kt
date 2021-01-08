package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqKeywordDelete(
    @SerializedName("keywords")
    val keywords: List<String>
)