package com.muni.taskmajster.view.ui.gameplan.gameplan_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.common.TopBarButton
import com.muni.taskmajster.view.ui.components.dialog.CustomAlertDialog
import com.muni.taskmajster.view.ui.components.list_item.TaskItem

@Composable
fun GameplanDetail(
    gameplan: Gameplan,
    listOfGameplanTasks: List<Task>,

    onArrowBackClicked: () -> Unit,
    onTaskClicked: (Task) -> Unit,
    onPlayClicked: (Game) -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    onAddNewTaskClicked: () -> Unit,

    onRemoveFromGameplanClicked: (Task) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        CustomAlertDialog(
            title = "Delete gameplan",
            description = "Do you really want to delete gameplan " + gameplan.name + "?",
            confirmText = "Delete",
            onConfirmClicked = onDeleteClicked,
            onDismiss = { showDeleteDialog = false },
            showCancel = true
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Gameplan: ${gameplan.name}",
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = listOf(
                    TopBarButton(
                        onClicked = onEditClicked,
                        icon = Icons.Default.Edit,
                        contentDescription = "Edit"
                    ),
                    TopBarButton(
                        onClicked = { showDeleteDialog = true },
                        icon = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                LargeButton(
                    text = "Play Now",
                    icon = ButtonIcon.Vector(Icons.Outlined.PlayArrow),
                    onClicked = { onPlayClicked(
                        Game(
                            id = System.currentTimeMillis(),
                            currentTask = 0,
                            gameplan = gameplan,
                            listOfPlayers = emptyList(),
                            )
                    )}
                )
            }

            HorizontalDivider(thickness = 1.dp, color = Color.Black)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(listOfGameplanTasks) { _, task ->
                        TaskItem(
                            onTaskClicked = { onTaskClicked(task) },
                            task = task,
                            inGameplanInfo = true,
                            onRemoveFromGameplanClicked = { onRemoveFromGameplanClicked(task) }
                        )
                    }
                }
            }

            Column {
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LargeButton(
                        "Add new task",
                        ButtonIcon.Vector(Icons.Outlined.AddCircle),
                        onClicked = { onAddNewTaskClicked() },
                        true,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GameplanDetailPreview() {
    GameplanDetail (
        gameplan = Gameplan(
            "1",
            "The gameplan",
            listOfTaskIds = List(10) { index ->
                "$index"
            },),
        listOfGameplanTasks = List(10) { index ->
            Task(
                id = index.toString(),
                name = "Task $index",
                time = (10..120).random(),
                description = "Description for Task $index.",
                imagePaths = emptyList()
            )
        } ,
        onArrowBackClicked = {},
        onTaskClicked = {},
        onPlayClicked = {},
        onEditClicked = {},
        onDeleteClicked = {},
        onAddNewTaskClicked = {},
        onRemoveFromGameplanClicked = {}
    )
}