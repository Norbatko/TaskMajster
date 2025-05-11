package com.muni.taskmajster.view.ui.main_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.components.button.MenuButton
import com.muni.taskmajster.view.ui.components.common.TopBar

@Composable
fun MainPage (
    onTasksClicked: () -> Unit,
    onGameplansClicked: () -> Unit,
    onPlayRandomClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "TaskMajster",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                MenuButton(
                    text = "Play random",
                    icon = Icons.Default.Refresh,
                    onClicked = onPlayRandomClicked
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                MenuButton(
                    text = "Tasks",
                    icon = Icons.Default.Menu,
                    onClicked = onTasksClicked
                )
                MenuButton(
                    text = "Gameplans",
                    icon = Icons.AutoMirrored.Filled.List,
                    onClicked = onGameplansClicked
                )
            }
        }
    }
}


@Preview
@Composable
fun MainPagePreview() {
    MainPage(
        onTasksClicked = { },
        onGameplansClicked = { },
        onPlayRandomClicked = { },
    )
}