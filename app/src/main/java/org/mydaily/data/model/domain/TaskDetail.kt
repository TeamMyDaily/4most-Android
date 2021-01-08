package org.mydaily.data.model.domain

data class TaskDetail(
    val id: Int,
    val TotalKeywordId: Int,
    val title: String,
    val detail: String,
    val satisfaction: Int
)
