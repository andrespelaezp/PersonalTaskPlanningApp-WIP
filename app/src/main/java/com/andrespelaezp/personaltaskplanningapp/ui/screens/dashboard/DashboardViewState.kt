package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import com.andrespelaezp.datasourcecompiler.data.Task
import com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard.dashboardGrid.DashboardGridItem

data class DashboardViewState(
    val todayTasks: List<Task> = emptyList(),
    val thisWeekTasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val completionMarkers: List<DashboardGridItem> = emptyList(),
)