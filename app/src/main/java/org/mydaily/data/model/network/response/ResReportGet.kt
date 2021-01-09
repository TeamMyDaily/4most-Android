package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResReportGet(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("keywordName")
        val keywordName: String,
        @SerializedName("taskCnt")
        val taskCnt: Int,
        @SerializedName("taskSatisAvg")
        val taskSatisAvg: String,
        @SerializedName("weekGoal")
        val weekGoal: String
    )
}