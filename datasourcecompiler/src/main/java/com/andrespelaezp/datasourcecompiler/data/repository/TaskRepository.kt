package com.andrespelaezp.datasourcecompiler.data.repository

import android.util.Log
import com.andrespelaezp.datasourcecompiler.data.Task
import com.andrespelaezp.datasourcecompiler.api.services.google.GoogleTasksService
import com.andrespelaezp.datasourcecompiler.api.services.jira.JiraService
import com.andrespelaezp.datasourcecompiler.api.services.openproject.OpenProjectService
import com.andrespelaezp.datasourcecompiler.data.dao.TaskDao
import com.andrespelaezp.datasourcecompiler.data.repository.mapper.mapGoogleTask
import com.andrespelaezp.datasourcecompiler.data.repository.mapper.mapJiraTask
import com.andrespelaezp.datasourcecompiler.data.repository.mapper.mapOpenProjectTask
import com.andrespelaezp.datasourcecompiler.data.repository.mapper.toOpenProjectWorkPackage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(
//    private val jiraService: JiraService?,
//    private val googleTasksService: GoogleTasksService?,
    private val openProjectService: OpenProjectService,
    private val taskDao: TaskDao
) {

    suspend fun fetchAllTasks(
        jiraAuthToken: String,
        googleAuthToken: String,
        googleTaskListId: String
    ): List<Task> = withContext(Dispatchers.IO) {
//        val jiraTasks = fetchJiraTasks(jiraAuthToken)
//        val googleTasks = fetchGoogleTasks(googleAuthToken, googleTaskListId)
//        val openProjectTasks =

//        jiraTasks + googleTasks +
        return@withContext fetchOpenProjectTasks()
    }

    // Sync unsynced data when the network is available
    suspend fun syncUnsyncedWorkPackages() {
        val unsyncedPackages = taskDao.getUnsyncedWorkPackages()
        for (workPackage in unsyncedPackages) {
            try {
                val response = openProjectService.updateWorkPackage(
                    workPackageId = workPackage.id,
                    workPackage = workPackage.toOpenProjectWorkPackage()
                )
                if (response.isSuccessful &&
                    (response.body()?.lockVersion ?: 0) > (response.body()?.lockVersion ?: 0)
                ) {
                    val updatedPackage = workPackage.copy(isSynced = true)
                    taskDao.updateWorkPackage(updatedPackage)
                }
            } catch (e: Exception) {
                Log.e("SyncError", "Failed to sync Work Package ${workPackage.id}")
            }
        }
    }

    private suspend fun fetchOpenProjectTasks(): List<Task> {
        val response = openProjectService.getWorkPackagesByProject("zproject")
        if (response.isSuccessful.not()) {
            Log.e("WorkPackageRepo", "Error fetching work packages: ${response.message()}")
            return emptyList()
        }
        response.body()?.let {
            val tasks = mapOpenProjectTask(it)
            Log.d("fetchOpenProjectTasks", "Saving OpenProject tasks workPackage: ${tasks.size}")
            storeWorkPackages(tasks)
            return tasks
        }
        return emptyList()
    }

    // Fetch from API when online, otherwise return local data
    private suspend fun storeWorkPackages(tasks: List<Task>) {
        try {
            withContext(Dispatchers.IO) {
                taskDao.insertAll(tasks)
            }
        } catch (e: Exception) {
            Log.e("WorkPackageRepo", "Error fetching work packages: ${e.message}")
        }
    }

    suspend fun getTodayTasks(): List<Task> {
        return taskDao.getAllWorkPackages()
    }

    fun getThisWeekTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    fun getCompletionMarkers(): List<Task> {
        TODO("Not yet implemented")
    }

//    private suspend fun fetchJiraTasks(authToken: String): List<Task> {
//        val response = jiraService.getTasks("order by created DESC", "Bearer $authToken")
//        return mapJiraTask(response.issues)
//    }
//
//    private suspend fun fetchGoogleTasks(authToken: String, taskListId: String): List<Task> {
//        val response = googleTasksService.getTasks(taskListId, "Bearer $authToken")
//        return mapGoogleTask(response.items?: emptyList())
//    }

}