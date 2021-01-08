package org.mydaily.data.model.network.request


import com.google.gson.annotations.SerializedName

data class ReqKeywordPriority(
    @SerializedName("keywords")
    val keywords: List<Keyword>
) {
    data class Keyword(
        @SerializedName("name")
        val name: String,
        @SerializedName("priority")
        val priority: Int
    )
}