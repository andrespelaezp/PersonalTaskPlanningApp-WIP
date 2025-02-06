package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrespelaezp.datasourcecompiler.repository.TaskRepository
import com.andrespelaezp.personaltaskplanningapp.keys.apiKey
import com.andrespelaezp.personaltaskplanningapp.keys.googleKey
import com.andrespelaezp.personaltaskplanningapp.keys.googleTaskId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val taskRepository: TaskRepository,
): ViewModel() {

    private val _state = MutableStateFlow(DashboardViewState())
    val state = _state.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _state.value = DashboardViewState(isLoading = true)
            try {
                val items = taskRepository.fetchAllTasks(
                    jiraAuthToken = apiKey,
                    googleAuthToken = googleKey,
                    googleTaskListId = googleTaskId,
                )
                _state.value = DashboardViewState(tasks = items)
            } catch (e: Exception) {
                _state.value = DashboardViewState(errorMessage = e.message)
            }
        }
    }
}