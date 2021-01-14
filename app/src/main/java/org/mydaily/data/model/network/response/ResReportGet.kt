package org.mydaily.data.model.network.response


import com.google.gson.annotations.SerializedName

data class ResReportGet(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("keywordsExist")
        val keywordsExist: Boolean,
        @SerializedName("result")
        val result: List<Result>
    ) {
        data class Result(
            @SerializedName("totalKeywordId")
            val Id: Int,
            @SerializedName("keyword")
            val keyword: String,
            @SerializedName("weekGoal")
            val weekGoal: String,
            @SerializedName("taskCnt")
            val taskCnt: Int,
            @SerializedName("taskSatisAvg")
            val taskSatisAvg: String
        )
    }
}
