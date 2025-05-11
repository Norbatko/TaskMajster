package com.muni.taskmajster.view.ui.main_page

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.components.button.MenuButton
import com.muni.taskmajster.view.ui.components.common.CustomPageContentWrapper
import com.muni.taskmajster.view.ui.components.common.customBorder

@Composable
fun MainPage (
    onTasksClicked: () -> Unit,
    onGameplansClicked: () -> Unit,
    onPlayRandomClicked: () -> Unit,
) {
    Scaffold { innerPadding ->
        CustomPageContentWrapper(
            innerPadding = innerPadding
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    TitleContainer("Taskmajster")
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
}

@Composable
fun TitleContainer(text: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 40.dp)
            .fillMaxWidth(0.8f)
            .height(100.dp)
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = CircleShape
            )
            .border(customBorder(), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
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