package org.mydaily.data.model.domain

data class Task(
    val id: Int,
    val name: String,
    val priority: Int,
    val tasks: List<String>
)
