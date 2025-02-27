package com.andrespelaezp.datasourcecompiler.data.repository.mapper

import com.andrespelaezp.datasourcecompiler.api.data.google.GoogleTask
import com.andrespelaezp.datasourcecompiler.api.data.jira.JiraTask
import com.andrespelaezp.datasourcecompiler.api.data.openproject.Description
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackage
import com.andrespelaezp.datasourcecompiler.api.data.openproject.WorkPackagesListResponse
import com.andrespelaezp.datasourcecompiler.data.SourceType
import com.andrespelaezp.datasourcecompiler.data.Task


fun mapOpenProjectTask(openProjectTasks: WorkPackagesListResponse): List<Task> {
    return openProjectTasks.embedded.workPackages.map { workPackage ->
        Task(
            id = "${workPackage.id}",
            title = workPackage.subject,
            summary = workPackage.description?.raw?: "",
            sourceType = SourceType.OPEN_PROJECT,
            status = workPackage.status?: "",
            dueDate = workPackage.dueDate,
            createdAt = workPackage.createdAt,
            updatedAt = workPackage.updatedAt,
            lockVersion = workPackage.lockVersion,
            isSynced = false
        )
    }
}

fun Task.toOpenProjectWorkPackage(): WorkPackage {
    return WorkPackage(
        id = id.toInt(),
        subject = title,
        description = Description(raw = summary),
        status = status,
        dueDate = dueDate,
        createdAt = createdAt?: "",
        lockVersion = lockVersion?: 0,
        updatedAt = updatedAt?: ""
    )
}

fun mapJiraTask(jiraTasks: List<JiraTask>): List<Task> {
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

fun mapGoogleTask(googleTasks: List<GoogleTask>): List<Task> {
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