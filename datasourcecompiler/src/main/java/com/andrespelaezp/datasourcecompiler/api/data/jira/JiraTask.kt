package com.andrespelaezp.datasourcecompiler.api.data.jira

class JiraTask(
    val id: String,
    val title: String,
    val key: String,
    val fields: JiraTaskFields,
    val dueDate: String? = null
)
