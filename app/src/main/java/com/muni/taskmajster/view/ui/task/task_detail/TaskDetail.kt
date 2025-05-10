package com.muni.taskmajster.view.ui.task.task_detail

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.util.GalleryRetrieveUtil
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.common.TopBarButton
import com.muni.taskmajster.view.ui.components.dialog.CustomAlertDialog
import java.io.File

@Composable
fun TaskDetail(
    task: Task,
    onArrowBackClicked: () -> Unit,
    onAddToGameplanClicked: () -> Unit,
    onPlayClicked: (Game) -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        CustomAlertDialog(
            title = "Delete task",
            description = "Do you really want to delete task " + task.name + "?",
            confirmText = "Delete",
            onConfirmClicked = onDeleteClicked,
            onDismiss = { showDeleteDialog = false },
            showCancel = true
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Task: ${task.name}",
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
                            id = System.currentTimeMillis(),
                            currentTask = 0,
                            gameplan = Gameplan(
                                id = System.currentTimeMillis().toString() + 1,
                                name = "Task: " + task.name,
                                listOfTaskIds = List(1){ "1" },
                            ),
                            listOfPlayers = emptyList(),
                        )
                    )}
                )

                Spacer(modifier = Modifier.size(16.dp))

                LargeButton(
                    text = "Add to plan",
                    icon = ButtonIcon.Vector(Icons.AutoMirrored.Default.List),
                    onClicked = { onAddToGameplanClicked() }
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
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val imageUris = GalleryRetrieveUtil.getImagesByNameFromTaskMajsterFolder(context = LocalContext.current, targetFilenames = photoList)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val rows = imageUris.chunked(3)
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { uri ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                            .clickable { selectedImageUri = uri },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    // Fullscreen image preview dialog
    selectedImageUri?.let { uri ->
        Dialog(onDismissRequest = { selectedImageUri = null }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { selectedImageUri = null },  // Close dialog when clicked outside
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = uri,
                    contentDescription = "Full Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
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
        onAddToGameplanClicked = {},
        onPlayClicked = {},
        onEditClicked = {},
        onDeleteClicked = {},
    )
}