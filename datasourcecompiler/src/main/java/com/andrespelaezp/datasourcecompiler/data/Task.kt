package com.andrespelaezp.datasourcecompiler.data

data class Task(
    val id: String,
    val title: String,
    val summary: String? = null,
    val sourceType: SourceType,
    val status: String,
    val dueDate: String? = null,
)