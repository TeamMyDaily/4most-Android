package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResTaskGet(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("name")
        val name: String,
        @SerializedName("priority")
        val priority: Int,
        @SerializedName("tasks")
        val tasks: List<Any>
    )
}