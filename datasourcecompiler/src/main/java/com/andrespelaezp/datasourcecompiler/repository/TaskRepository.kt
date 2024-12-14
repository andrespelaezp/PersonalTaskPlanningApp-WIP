package com.andrespelaezp.datasourcecompiler.repository

import com.andrespelaezp.datasourcecompiler.api.data.SourceType
import com.andrespelaezp.datasourcecompiler.api.data.Task
import com.andrespelaezp.datasourcecompiler.api.data.google.GoogleTask
import com.andrespelaezp.datasourcecompiler.api.data.jira.JiraTask
import com.andrespelaezp.datasourcecompiler.api.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.jira.JiraService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val jiraService: JiraService,
    private val googleTasksService: GoogleTasksService
) {

    suspend fun fetchAllTasks(
        jiraAuthToken: String,
        googleAuthToken: String,
        googleTaskListId: String
    ): List<Task> = withContext(Dispatchers.IO) {
        val jiraTasks = fetchJiraTasks(jiraAuthToken)
        val googleTasks = fetchGoogleTasks(googleAuthToken, googleTaskListId)

        return@withContext jiraTasks + googleTasks
    }

    private suspend fun fetchJiraTasks(authToken: String): List<Task> {
        val response = jiraService.getTasks("order by created DESC", "Bearer $authToken")
        return mapJiraTask(response.issues)
    }

    private suspend fun fetchGoogleTasks(authToken: String, taskListId: String): List<Task> {
        val response = googleTasksService.getTasks(taskListId, "Bearer $authToken")
        return mapGoogleTask(response.items?: emptyList())
    }

    private fun mapJiraTask(jiraTasks: List<JiraTask>): List<Task> {
        return jiraTasks.map { issue ->
            Task(
                id = issue.id,
                title = issue.title,
                summary = issue.fields.summary,
                sourceType = SourceType.JIRA,
                status = issue.fields.status.name,
                dueDate = issue.dueDate
            )
        }
    }

    private fun mapGoogleTask(googleTasks: List<GoogleTask>): List<Task> {
        return googleTasks.map { task ->
            Task(
                id = task.id,
                title = task.title,
                sourceType = SourceType.GOOGLE,
                status = task.status,
                dueDate = task.due
            )
        }
    }
}