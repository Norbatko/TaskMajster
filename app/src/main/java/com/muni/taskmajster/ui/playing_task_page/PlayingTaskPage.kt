package com.muni.taskmajster.ui.playing_task_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PlayingTaskPage() {
    Scaffold(
        topBar = {
            PlayingTaskTopBar(
                title = "Task 1",
                onArrowBackClicked = { println("Back button clicked") }
            )
        },
        content = { innerPadding -> PlayingTaskContent(paddingValues = innerPadding) }
    )
}

@Composable
fun PlayingTaskTopBar(
    title: String,
    onArrowBackClicked: () -> Unit
) {
    Row (
        Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onArrowBackClicked,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
                Text("Done")
            },
        )
    }
}

@Composable
fun PlayingTaskContent(paddingValues: PaddingValues) {
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        Button(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Text("Play")
            },
        )
    }

}

@Preview
@Composable
fun PlayingTaskPagePreview() {
    PlayingTaskPage()
}