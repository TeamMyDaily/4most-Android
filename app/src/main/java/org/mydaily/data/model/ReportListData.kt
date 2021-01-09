package org.mydaily.data.model

data class ReportListData (
    val daily_record: List<Daily>,
    val keyword: String,
    val Keyword_goal: String,
    val rate: Double,
    val task_num: Int
){
    data class Daily(
        val date: String,
        val record: String,
        val satisfaction: Int
    )
}