package org.mydaily.data.model.domain

data class Goal (
    val priority: Int,
    val totalKeywordId: Int,
    val name: String,
    val weekGoalId: Int,
    val weekGoal: String,
    val isGoalCreated: Boolean,
    val isGoalCompleted: Boolean
)