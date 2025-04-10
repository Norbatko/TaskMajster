package com.muni.taskmajster.ui.gameplan_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.data.Task
import com.muni.taskmajster.ui.components.button.ButtonIcon
import com.muni.taskmajster.ui.components.button.LargeButton
import com.muni.taskmajster.ui.components.common.TopBar
import com.muni.taskmajster.ui.components.common.TopBarButton
import com.muni.taskmajster.ui.components.list_item.TaskItem

@Composable
fun GameplanDetail(
    gameplan: Gameplan,
    onArrowBackClick: () -> Unit,
    onTaskClick: (Task) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = gameplan.name,
                onArrowBackClicked = onArrowBackClick,
                sideButtons = listOf(
                    TopBarButton(onClick = { }, icon = Icons.Default.Delete, contentDescription = "Delete")
                ),
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
                    onClick = { }
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
                    itemsIndexed(gameplan.listOfTasks) { _, task ->
                        TaskItem(
                            onTaskClick = { onTaskClick(task) }, // <- Clickable
                            task = task
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
                        onClick = {},
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
            1,
            "The gameplan",
            listOfTasks = List(10) { index ->
                Task(
                    id = index.toLong(),
                    name = "Task $index",
                    time = (10..120).random(),
                    description = "Description for Task $index.",
                    images = emptyList()
                )
            },),
        onArrowBackClick = {},
        onTaskClick = {}
    )
}