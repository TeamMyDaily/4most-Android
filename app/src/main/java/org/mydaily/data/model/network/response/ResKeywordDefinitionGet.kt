package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResKeywordDefinitionGet(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val `data`: Data
) {
    data class Data(
        @SerializedName("isWritten")
        val isWritten: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("definition")
        val definition: String
    )
}