package com.andrespelaezp.personaltaskplanningapp.ui.screens.main

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard.DashboardScreen
import com.andrespelaezp.personaltaskplanningapp.ui.screens.new_task.CreateTaskView
import com.andrespelaezp.personaltaskplanningapp.ui.screens.task_detail.TaskDetailScreen
import com.andrespelaezp.personaltaskplanningapp.ui.screens.tasks.TasksView
import kotlinx.coroutines.CoroutineScope

@Composable
fun AppMainScreen(
    context: Context
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text("Main Menu", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                HorizontalDivider()

                Text("Tasks", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                NavigationDrawerItem(
                    label = { Text("Dashboard - WIP") },
                    selected = false,
                    onClick = {
                        navController.navigate("dashboard")
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Board view (TODO)") },
                    selected = false,
                    onClick = { /* TODO Handle click */ }
                )
                NavigationDrawerItem(
                    label = { Text("Stats (TODO)") },
                    selected = false,
                    onClick = {
                        navController.navigate("stats")
                    }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                Text("Settings", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                NavigationDrawerItem(
                    label = { Text("Provider List (TODO)") },
                    selected = false,
                    icon = { Icon(Icons.AutoMirrored.Outlined.List, contentDescription = null) },
                    onClick = { /* TODO Handle click */ },
                )
                Spacer(Modifier.height(12.dp))
                NavigationDrawerItem(
                    label = { Text("Preferences (TODO)") },
                    selected = false,
                    icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
//                    badge = { Text("20") }, // Placeholder
                    onClick = { /* TODO Handle click */ }
                )
            }
        },
    ) {
        Scaffold(
            topBar = { TopBar(scope, drawerState) },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("New Task") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        navController.navigate("create_task")
                    }
                )
            }
        ) { contentPadding ->
            // Screen content
            NavHost(navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(contentPadding)
            ) {
                composable("dashboard") {
                    DashboardScreen(
                        context = context,
                        navController = navController,
                        navigateToTask = { taskId ->
                            navController.navigate("detail/$taskId")
                        }
                    )
                }
                composable("tasks") { TasksView(navController = navController) }
                composable("stats") {
                    //TODO: Stats screen
                }
                composable("detail/{task}") { backStackEntry ->
                    //TODO: Detail screen
                    TaskDetailScreen(backStackEntry.arguments?.getString("task") ?: "Unknown")
                }
                composable("create_task") {
                    //TODO: Create task screen
                    CreateTaskView(navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scope: CoroutineScope, drawerState: DrawerState) {
    TopAppBar(
        title = { Text("Open Source TODO App") },
        navigationIcon = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}