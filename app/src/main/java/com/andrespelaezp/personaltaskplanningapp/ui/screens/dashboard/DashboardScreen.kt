package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreen(
    context: Context,
    viewModel: DashboardViewModel = koinViewModel<DashboardViewModel>(),
    navController: NavHostController
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle()

    DashboardScreenContent(
        uiState = uiState.value,
        context = context
    )
}

@Composable
fun DashboardScreenContent(
    uiState: DashboardViewState,
    context: Context
) {
    if (uiState.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    } else if (uiState.errorMessage != null) {
        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}