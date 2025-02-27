package com.andrespelaezp.datasourcecompiler.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey
    val id: String,
    val title: String,
    val summary: String? = null,
    val sourceType: SourceType,
    val status: String,
    val dueDate: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val lockVersion: Int? = 0,
    val isSynced: Boolean = false
)