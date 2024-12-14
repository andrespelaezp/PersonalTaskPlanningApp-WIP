package com.andrespelaezp.datasourcecompiler.api

import com.andrespelaezp.datasourcecompiler.api.data.Task

interface TaskService {

    suspend fun getTasks(): List<Task>
    //TODO: Implement the following methods
    //    fun addTask(task: Task):
    //    fun removeTask(task: Task):
    //    fun updateTask(task: Task):

}