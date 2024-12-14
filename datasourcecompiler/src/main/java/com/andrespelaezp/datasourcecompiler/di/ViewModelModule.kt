package com.andrespelaezp.datasourcecompiler.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrespelaezp.datasourcecompiler.api.data.Task
import com.andrespelaezp.datasourcecompiler.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    fun fetchTasks(
        jiraAuthToken: String,
        googleAuthToken: String,
        googleTaskListId: String
    ) {
        viewModelScope.launch {
            try {
                _tasks.value = repository.fetchAllTasks(
                    jiraAuthToken,
                    googleAuthToken,
                    googleTaskListId
                )
            } catch (e: Exception) {
                // Handle exceptions (e.g., show error to user)
            }
        }
    }
}