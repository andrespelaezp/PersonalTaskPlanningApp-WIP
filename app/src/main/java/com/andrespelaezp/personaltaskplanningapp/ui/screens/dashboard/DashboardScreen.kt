package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrespelaezp.datasourcecompiler.api.data.SourceType
import com.andrespelaezp.datasourcecompiler.api.data.Task
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel<DashboardViewModel>()
) {

    val uiState = viewModel.state.collectAsStateWithLifecycle()

    DashboardScreenContent(
        uiState = uiState.value
    )
}

@Composable
fun DashboardScreenContent(
    uiState: DashboardViewState
) {


}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreenContent(
        uiState = DashboardViewState(
            tasks = listOf(
                Task(
                    title = "Task 1",
                    dueDate = "Due date 1",
                    sourceType = SourceType.JIRA,
                    id = "",
                    status = ""
                ),
                Task(
                    title = "Task 2",
                    sourceType = SourceType.JIRA,
                    id = "",
                    status = ""
                ),
                Task(
                    title = "Task 3",
                    sourceType = SourceType.JIRA,
                    id = "",
                    status = ""
                ),
            )
        )
    )
}