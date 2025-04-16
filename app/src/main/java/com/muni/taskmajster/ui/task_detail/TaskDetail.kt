package com.muni.taskmajster.ui.task_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.muni.taskmajster.data.Game
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.data.Task
import com.muni.taskmajster.ui.components.button.ButtonIcon
import com.muni.taskmajster.ui.components.button.LargeButton
import com.muni.taskmajster.ui.components.common.TopBar
import com.muni.taskmajster.ui.components.common.TopBarButton
import java.io.File

@Composable
fun TaskDetail(
    task: Task,
    onArrowBackClicked: () -> Unit,
// onAddToPlanClicked: () -> Unit, // TODO add to existing gameplan -> viz TaskItem onAddToListClicked attribute (maybe create new page for it?)
    onPlayClicked: (Game) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Task: ${task.name}",
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = listOf(
                    TopBarButton(onClicked = { }, icon = Icons.Default.Edit, contentDescription = "Edit"),
                    TopBarButton(onClicked = { }, icon = Icons.Default.Delete, contentDescription = "Delete"),
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LargeButton(
                    text = "Play Now",
                    icon = ButtonIcon.Vector(Icons.Outlined.PlayArrow),
                    onClicked = { onPlayClicked(
                        Game(
                            id = 1, // Todo generate
                            currentTask = 0,
                            gameplan = Gameplan(
                                id = "1",  // Todo generate
                                name = "Task: " + task.name,
                                listOfTasks = List(1){ task },
                            ),
                            listOfPlayers = emptyList(),
                        )
                    )}
                )

                Spacer(modifier = Modifier.size(16.dp))

                LargeButton(
                    text = "Add to plan",
                    icon = ButtonIcon.Vector(Icons.AutoMirrored.Default.List),
                    onClicked = { }
                )
            }
            Text(
                text = task.description,
                Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
            )

            Spacer(modifier = Modifier.size(32.dp))

            Text(
                text = "Time to complete: ${task.time} seconds",
                modifier = Modifier.padding(10.dp)
            )

            HorizontalDivider(thickness = 2.dp, color = Color.Black)

            Text(
                text = "Photos from previous games",
                fontSize = 25.sp,
                modifier = Modifier.padding(20.dp))

            PhotoGrid(task.imagePaths)
        }
    }
}

@Composable
fun PhotoGrid(photoList: List<String>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val file = File("/storage/emulated/0/Pictures/IMG_20250322_193456.jpg")

        val rows = photoList.chunked(3)
        for (row in rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (photoResId in row) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(120.dp)  // Ensure a fixed size
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = file,
                            contentDescription = "Stored Image",
                            modifier = Modifier.size(100.dp)
                        )
//                        Text(
//                            text = "Photo $photoResId",
//                            color = Color.White,
//                            style = MaterialTheme.typography.bodySmall
//                        )
                    }
                }
            }
        }
    }
}

// TODO What is this?
@Composable
fun PhotoGallery(photoList: List<Int>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photoList.size) { photoResId ->
            val painter = runCatching {
                painterResource(id = photoResId)
            }.getOrNull()

            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = "Gallery Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                )
            } else {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Image Not Found",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskDetailPreview() {
    TaskDetail(
        task = Task("1", "task1", 15, "task description", emptyList()),
        onArrowBackClicked = {},
        onPlayClicked = {}
    )
}