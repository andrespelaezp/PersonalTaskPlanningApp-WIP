package com.andrespelaezp.personaltaskplanningapp.ui.screens.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TasksView(navController: NavHostController) {
    TodoListScreen(navController)
}

@Composable
fun TodoListScreen(navController: NavHostController) {
    val tasks = listOf("Data layer", "API", "Database", "Dashboard Design", "UI", "Testing", "Deployment")
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        tasks.forEach { task ->
            Text(task, modifier = Modifier.fillMaxWidth().clickable {
                navController.navigate("detail/$task")
            }.padding(8.dp))
        }
    }
}