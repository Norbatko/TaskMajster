package com.muni.taskmajster.view.ui.gameplan.list_of_gameplans

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
import com.muni.taskmajster.view.ui.components.list_item.GameplanItem

@Composable
fun ListOfGameplans(
    listOfGameplans: List<Gameplan>,
    onArrowBackClicked: () -> Unit,
    onGameplanClicked: (Gameplan) -> Unit,
    onAddGameplanClicked: () -> Unit,
    // Attributes for adding task to gameplan
    addTaskToGameplan: Boolean = false,
    task: Task? = null,
    onAddTaskToGameplanClicked: (Gameplan) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "List of gameplans",
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = if (!addTaskToGameplan) {
                    listOf(
                    TopBarButton(
                        onClicked = onAddGameplanClicked,
                        icon = Icons.Default.Add,
                        contentDescription = "Add"
                    ))
                } else { emptyList() },
            )
        },
    ) { innerPadding ->
        CustomPageContentWrapper(
            innerPadding = innerPadding
        ) {
            LazyColumn {
                items(items = listOfGameplans, key = { it.id }) { gameplan ->
                    if (addTaskToGameplan) {
                        GameplanItem(
                            gameplan = gameplan,
                            addToGameplan = !gameplan.listOfTaskIds.contains(task?.id), // only if gameplan doesn't already have the task
                            onAddToGameplan = { onAddTaskToGameplanClicked(gameplan) }
                        )
                    } else {
                        GameplanItem(gameplan = gameplan, onGameplanClicked = onGameplanClicked)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ListOfGameplansPreview() {
    ListOfGameplans(
        listOfGameplans = List(20) { index ->
            Gameplan(
                id = index.toString(),
                name = "Gameplan $index",
                listOfTaskIds = emptyList(),
            )
        },
        onArrowBackClicked = {},
        onGameplanClicked = {},
        onAddGameplanClicked = {}
    )
}
