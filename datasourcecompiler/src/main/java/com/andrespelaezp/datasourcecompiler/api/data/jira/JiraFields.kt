package com.andrespelaezp.datasourcecompiler.api.data.jira

data class JiraTaskFields(
    val summary: String,
    val status: JiraTaskStatus
)