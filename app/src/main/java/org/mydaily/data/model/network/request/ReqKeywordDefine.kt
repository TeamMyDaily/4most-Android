package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqKeywordDefine(
    @SerializedName("name")
    val name: String,
    @SerializedName("definition")
    val definition: String
)