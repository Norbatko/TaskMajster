package com.muni.taskmajster.ui.end_of_task_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MailOutline
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
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.ui.components.player.PlayerWithScore
import kotlin.random.Random

@Composable
fun EndOfTaskPage(
    taskName: String,
    onArrowBackClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            EndOfTaskTopBar(
                title = taskName,
                onArrowBackClicked = onArrowBackClicked
            )
        },
    ) { innerPadding ->
        EndOfTaskPageContent(innerPadding)
    }
}

@Composable
fun EndOfTaskTopBar(
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
    }
}

@Composable
fun EndOfTaskPageContent(
    padding: PaddingValues
    // arg: list of players
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding)

    ) {
        // players
        FinalScoreBoard()
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.MailOutline,
                    contentDescription = null)
            }
            Text(
                "Take a photo",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Button(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
                Text(
                    "Next task",
                    style = MaterialTheme.typography.labelLarge)
            },
            modifier = Modifier.padding(20.dp)
        )

        Button(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
                Text(
                    "Finalize",
                    style = MaterialTheme.typography.labelLarge)
            },
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun FinalScoreBoard() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(5) {
            PlayerWithScore(
                name = "Player ${it + 1}",
                color = Random.nextInt(),
                numberOfPoints = Random.nextInt(5, 10),
                showScoreSetter = false
            )
        }
    }
}

@Preview
@Composable
fun EndOfTaskPagePreview() {
    EndOfTaskPage(
        taskName = "Task X",
        onArrowBackClicked = {}
    )
}