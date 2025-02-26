package com.andrespelaezp.datasourcecompiler.api.data.openproject

data class ProjectResponse(
    val id: Int,
    val name: String,
    val identifier: String,
    val createdAt: String
)