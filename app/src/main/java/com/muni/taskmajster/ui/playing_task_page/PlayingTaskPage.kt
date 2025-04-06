package com.muni.taskmajster.ui.playing_task_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.ui.components.common.PlayButton
import com.muni.taskmajster.ui.playing_task_page.scoring_bottom_sheet.ScoringBottomSheet




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayingTaskPage(
    taskName: String,
    taskDescription: String,
    onDoneClicked: () -> Unit,
    onArrowBackClicked: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            ScoringBottomSheet()
        },
        topBar = {
            PlayingTaskTopBar(
                title = taskName,
                onArrowBackClicked = onArrowBackClicked,
                onDoneClicked = onDoneClicked
            )
        },
    ) {
        innerPadding -> PlayingTaskContent(
            description = taskDescription
        )
    }
}

@Composable
fun PlayingTaskTopBar(
    title: String,
    onDoneClicked: () -> Unit,
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
            onClick = onDoneClicked,
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
fun PlayingTaskContent(
    description: String
) {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(20.dp)) {
        Text(
            description,
            style = MaterialTheme.typography.bodyLarge
        )
        PlayButton(onClick = {})
    }
}

@Preview
@Composable
fun PlayingTaskPagePreview() {
    PlayingTaskPage(
        taskName = taskName,
        taskDescription = taskDescription,
        onDoneClicked = {},
        onArrowBackClicked = {}
    )
}