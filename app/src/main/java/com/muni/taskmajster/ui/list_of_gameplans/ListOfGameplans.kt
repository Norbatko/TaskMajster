package com.muni.taskmajster.ui.list_of_gameplans

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.ui.components.common.TopBar
import com.muni.taskmajster.ui.components.common.TopBarButton
import com.muni.taskmajster.ui.components.list_item.GameplanItem

@Composable
fun ListOfGameplans(
    listOfGameplans: List<Gameplan>,
    onArrowBackClicked: () -> Unit,
    onGameplanClicked: (Gameplan) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title  = "List of gameplans",
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = listOf(
                    TopBarButton(onClicked = { }, icon = Icons.Default.Add, contentDescription = "Add"),
                )
            )
        },
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            items(items = listOfGameplans, key = { it.id }) { gameplan ->
                GameplanItem(gameplan = gameplan, onGameplanClicked = onGameplanClicked)
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
                id = index.toLong(),
                name = "Gameplan $index",
                listOfTasks = emptyList(),
            )
        },
        onArrowBackClicked = {},
        onGameplanClicked = {}
    )
}
