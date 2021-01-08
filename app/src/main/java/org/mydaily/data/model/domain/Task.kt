package org.mydaily.data.model.domain

data class Task(
    val id: Int,
    val name: String,
    val priority: Int,
    val tasks: List<Title>
) {
    data class Title(
        val id: Int,
        val title: String
    )
}
