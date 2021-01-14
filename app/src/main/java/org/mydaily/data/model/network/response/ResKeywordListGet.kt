package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResKeywordListGet(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("isSelected")
        val isSelected: Boolean,
        @SerializedName("name")
        val name: String
    )
}