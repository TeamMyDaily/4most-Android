package org.mydaily.data.model.network.request

import com.google.gson.annotations.SerializedName

data class ReqReportDetailGet(
    @SerializedName("start")
    val start: Long,
    @SerializedName("end")
    val end: Long,
    @SerializedName("totalKeywordId")
    val Id: Int
)
