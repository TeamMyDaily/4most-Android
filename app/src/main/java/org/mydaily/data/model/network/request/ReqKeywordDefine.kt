package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqKeywordDefine(
    @SerializedName("keywords")
    val keywords: List<Keyword>
) {
    data class Keyword(
        @SerializedName("definition")
        val definition: String,
        @SerializedName("name")
        val name: String
    )
}