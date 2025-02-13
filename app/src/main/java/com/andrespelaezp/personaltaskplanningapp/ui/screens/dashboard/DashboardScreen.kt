package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel<DashboardViewModel>(),
    navController: NavHostController
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