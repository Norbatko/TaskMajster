package com.muni.taskmajster.view.ui.task.list_of_tasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.common.CustomPageContentWrapper
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.common.TopBarButton
import com.muni.taskmajster.view.ui.components.list_item.TaskItem

@Composable
fun ListOfTasks(
    listOfTasks: List<Task>,
    onArrowBackClicked: () -> Unit,
    onTaskClicked: (Task) -> Unit,
    onAddTaskClicked: () -> Unit,
    // Attributes for adding task to gameplan
    addTaskToGameplan: Boolean = false,
    gameplan: Gameplan? = null,
    onAddTaskToGameplanClicked: (Gameplan, Task) -> Unit = { _, _ -> }
) {
    Scaffold(
        topBar = {
            TopBar(
                title  = "List of tasks",
                onArrowBackClicked = onArrowBackClicked,
                sideButtons= if (!addTaskToGameplan) {
                    listOf(
                        TopBarButton(
                            onClicked = onAddTaskClicked,
                            icon = Icons.Default.Add,
                            contentDescription = "Add"
                        ),
                    )
                } else {
                    emptyList()
                },
            )
        },
    ) { innerPadding ->
        CustomPageContentWrapper(
            innerPadding = innerPadding
        ) {
            LazyColumn {
                items(items = listOfTasks, key = { it.id }) { task ->
                    if (addTaskToGameplan) {
                        gameplan?.listOfTaskIds?.contains(task.id)?.let {
                            TaskItem(
                                task = task,
                                addToGameplan = !it, // only if gameplan doesn't already have the task
                                onAddToGameplanClicked = {
                                    onAddTaskToGameplanClicked(
                                        gameplan,
                                        task
                                    )
                                }
                            )
                        }
                    } else {
                        TaskItem(task = task, onTaskClicked = onTaskClicked)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ListOfTasksPreview() {
    ListOfTasks(
        listOfTasks = List(20) { index ->
            Task(
                id = index.toString(),
                name = "Task $index",
                time = (10..120).random(),
                description = "Description for Task $index.",
                imagePaths = emptyList()
            )
        },
        onArrowBackClicked = {},
        onTaskClicked = {},
        onAddTaskClicked = {}
    )
}
