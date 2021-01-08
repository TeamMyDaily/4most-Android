package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqKeywordAdd(
    @SerializedName("name")
    val name: String
)