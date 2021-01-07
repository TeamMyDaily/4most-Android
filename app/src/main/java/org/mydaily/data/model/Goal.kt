package org.mydaily.data.model

data class Goal(
    val id: Int,
    val keyword: String,
    val goal: String,
    val isGoalExist: Boolean,
    val isGoalCompleted: Boolean
)
