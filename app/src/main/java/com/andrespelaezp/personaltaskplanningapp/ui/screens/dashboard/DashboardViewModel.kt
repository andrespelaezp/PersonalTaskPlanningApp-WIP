package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrespelaezp.datasourcecompiler.data.repository.TaskRepository
import com.andrespelaezp.datasourcecompiler.keys.apiKey
import com.andrespelaezp.datasourcecompiler.keys.googleKey
import com.andrespelaezp.datasourcecompiler.keys.googleTaskId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val taskRepository: TaskRepository,
): ViewModel() {

    private val _state = MutableStateFlow(DashboardViewState())
    val state = _state.asStateFlow()

    init {
        updateTasks()
        loadDashboardData()
    }

    private fun updateTasks() {
        viewModelScope.launch {
            try {
                taskRepository.fetchAllTasks(
                    jiraAuthToken = apiKey,
                    googleAuthToken = googleKey,
                    googleTaskListId = googleTaskId,
                )
            } catch (e: Exception) {
                _state.value = DashboardViewState(errorMessage = e.message)
            }
        }
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _state.value = DashboardViewState(isLoading = true)
            try {
                val todayItems = taskRepository.getTodayTasks()
                val thisWeekItems = taskRepository.getThisWeekTasks()
                val completionMarkers = taskRepository.getCompletionMarkers()
                _state.value = DashboardViewState(
                    todayTasks = todayItems,
                    thisWeekTasks = thisWeekItems,
                    completionMarkers = emptyList()
                )
            } catch (e: Exception) {
                _state.value = DashboardViewState(errorMessage = e.message)
            }
        }
    }
}