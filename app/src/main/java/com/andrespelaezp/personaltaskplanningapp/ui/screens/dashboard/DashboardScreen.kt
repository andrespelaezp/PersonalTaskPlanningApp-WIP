package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Text
import com.andrespelaezp.datasourcecompiler.data.SourceType
import com.andrespelaezp.datasourcecompiler.data.Status
import com.andrespelaezp.datasourcecompiler.data.Task
import com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard.dashboardGrid.DashboardGridItem
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
        context = context,
        navigateToTask = { taskId ->
            navController.navigate("detail/$taskId")
        }
    )
}

@Composable
fun DashboardScreenContent(
    uiState: DashboardViewState,
    context: Context,
    navigateToTask: (String) -> Unit
) {
    if (uiState.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(36.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    } else if (uiState.errorMessage != null) {
        Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        DashboardContent(
            uiState.todayTasks,
            emptyList(),
            uiState.completionMarkers,
            navigateToTask
        )
    }
}

@Composable
fun DashboardContent(
    todayTasks: List<Task> = emptyList(),
    thisWeekTasks: List<Task> = emptyList(),
    lastWeekCompletion: List<DashboardGridItem> = emptyList(),
    onTaskClicked: (String) -> Unit
) {
    Column(modifier = Modifier.padding(5.dp)) {
        DashboardSectionTitle("Due Today")
        DashboardTaskList(todayTasks, onTaskClicked)
        Log.d("DashboardContent", "todayTasks: $todayTasks")

        DashboardSectionTitle("This Week's Tasks")
        DashboardTaskList(thisWeekTasks, onTaskClicked)

        DashboardSectionTitle("Last Week Completion")
        CompletionMetrics(
            lastWeekCompletion
        )
    }
}

@Composable
fun DashboardSectionTitle(title: String) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}

@Composable
fun DashboardTaskList(
    tasks: List<Task>,
    onTaskClicked: (String) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onTaskClicked)
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clickable {
                onTaskClicked(task.id)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = RoundedCornerShape(10),
        content = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Column (
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .weight(9f)
                ) {
                    Row {
                        TaskItemTitle(task)
                    }
                    TaskItemBody(task)
                }

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp)
                        .clip(RectangleShape)
                        .background(
                            task.status.color.toColorInt().let {
                                Color(it)
                            }
                        )
                )

            }


        }
    )
}

@Composable
private fun TaskItemTitle(task: Task) {
    Text(
        modifier = Modifier
            .padding(start = 12.dp, bottom = 5.dp, top = 8.dp),
        text = task.title,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 16.sp,
    )
}

@Composable
private fun TaskItemBody(task: Task) {
    Text(
        text = "Due to: ${task.dueDate}",
        color = Color.Black,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 16.dp, bottom = 5.dp)
    )
    Text(
        text = "Is synced: ${task.isSynced}",
        color = Color.Black,
        fontSize = 14.sp,
        modifier = Modifier.padding(start = 16.dp, bottom = 5.dp)
    )
}

@Composable
fun CompletionMetrics(data: List<DashboardGridItem>) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            data.map {
                item { MetricBox(it) }
            }
        }
    )
}

@Composable
fun MetricBox(item: DashboardGridItem) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(
                width = 0.5.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when(item) {
            is DashboardGridItem.TextItem -> MetricText(item.title, item.info)
            else -> {
                //TODO: Implement other types of metrics
//            is DashboardGridItem.ImageItem -> Icon(item.icon)
//            is DashboardGridItem.GraphItem -> Graph(item.value)
            }

        }
    }
}

@Composable
fun MetricText(
    title: String,
    info: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 32.dp, start = 32.dp, top = 4.dp, bottom = 4.dp)
        )
        Text(
            text = info,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL)
@Composable
fun PreviewTaskDashboard() {
    DashboardContent(
        todayTasks = DashboardScreenMock.uiState.todayTasks,
        thisWeekTasks = DashboardScreenMock.uiState.thisWeekTasks,
        lastWeekCompletion = DashboardScreenMock.uiState.completionMarkers,
        onTaskClicked = {}
    )
}

object DashboardScreenMock {
    val uiState = DashboardViewState(
        todayTasks = listOf(
            Task("Task 1", title = "Description 1", summary = "2022-10-10", dueDate = "2025-03-10", sourceType = SourceType.OPEN_PROJECT, status =  Status.CLOSED, isSynced = true, createdAt = "2022-10-10", updatedAt = "2022-10-10", lockVersion = 1),
            Task("Task 2", title = "Description 2", summary = "2022-10-10", dueDate = "2025-03-08", sourceType = SourceType.OPEN_PROJECT, status =  Status.IN_PROGRESS, isSynced = true, createdAt = "2022-10-10", updatedAt = "2022-10-10", lockVersion = 1),
        ),
        thisWeekTasks = listOf(
            Task("Task 3", title = "Description 2", summary = "2022-10-10", dueDate = "2025-03-10", sourceType = SourceType.OPEN_PROJECT, status =  Status.IN_PROGRESS, isSynced = true, createdAt = "2022-10-10", updatedAt = "2022-10-10", lockVersion = 1),
            Task("Task 4", title = "Description 2", summary = "2022-10-10", dueDate = "2025-03-08", sourceType = SourceType.OPEN_PROJECT, status =  Status.NEW, isSynced = false, createdAt = "2022-10-10", updatedAt = "2022-10-10", lockVersion = 1),
        ),
        completionMarkers = listOf(
            DashboardGridItem.TextItem("Completion Rate", "67%"),
            DashboardGridItem.TextItem("Missed Due Date", "2 tasks missed"),
            DashboardGridItem.TextItem("Completion Rate", "67%"),
            DashboardGridItem.TextItem("Tasks Completed", "10")
        )
    )
}
