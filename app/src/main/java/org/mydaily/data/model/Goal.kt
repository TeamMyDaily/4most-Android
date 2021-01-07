package org.mydaily.data.model

data class Goal(
    val keyword: String,
    val goal: String?,
    val isGoalExist: Boolean,
    val isGoalCompleted: Boolean
)
