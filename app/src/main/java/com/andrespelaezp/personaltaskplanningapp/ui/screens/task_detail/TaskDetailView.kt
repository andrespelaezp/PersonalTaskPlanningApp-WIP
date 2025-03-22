package com.andrespelaezp.personaltaskplanningapp.ui.screens.task_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TaskDetailScreen(task: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Task Details", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(task, style = MaterialTheme.typography.bodyMedium)
    }
}
