package com.muni.taskmajster.view.ui.task.task_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
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
import com.muni.taskmajster.R
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.ImageUriResult
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.util.GalleryRetrieveUtil
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.ui.components.common.CustomContainer
import com.muni.taskmajster.view.ui.components.common.CustomPageContentWrapper
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.common.TopBarButton
import com.muni.taskmajster.view.ui.components.dialog.CustomAlertDialog
import java.text.SimpleDateFormat
import java.util.Locale

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
                title = task.name,
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
        CustomPageContentWrapper(
            innerPadding = innerPadding
        ) {
            Column(
                modifier = Modifier
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
                        onClicked = {
                            onPlayClicked(
                                Game(
                                    id = System.currentTimeMillis(),
                                    currentTask = 0,
                                    gameplan = Gameplan(
                                        id = System.currentTimeMillis().toString() + 1,
                                        name = "Task: " + task.name,
                                        listOfTaskIds = List(1) { "1" },
                                    ),
                                    listOfPlayers = emptyList(),
                                )
                            )
                        }
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    LargeButton(
                        text = "Add to plan",
                        icon = ButtonIcon.Vector(Icons.AutoMirrored.Default.List),
                        onClicked = { onAddToGameplanClicked() }
                    )
                }

                CustomContainer {
                    Text(
                        text = task.description,
                    )
                }

                Spacer(modifier = Modifier.size(32.dp))

                CustomContainer {
                    Row (
                        verticalAlignment =  Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.id_clock_outline),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "Time to complete: ${task.time} seconds",
                        )
                    }

                }

                Spacer(modifier = Modifier.size(40.dp))

                CustomContainer {
                    Column {
                        Text(
                            text = "Photos from previous games",
                            style = MaterialTheme.typography.titleLarge,
                        )
                        PhotoGrid(task.imagePaths)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoGrid(photoList: List<String>) {
    var selectedImage by remember { mutableStateOf<ImageUriResult?>(null) }
    val imageUriList = GalleryRetrieveUtil.getImagesByNameFromTaskMajsterFolder(context = LocalContext.current, targetFilenames = photoList)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val rows = imageUriList.chunked(3)
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { uriResult ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                            .clickable { selectedImage = uriResult },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = uriResult.uri,
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
    selectedImage?.let { uriResult ->
        Dialog(onDismissRequest = { selectedImage = null }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { selectedImage = null },  // Close dialog when clicked outside
                contentAlignment = Alignment.Center
            ) {
                val date = extractDateFromFilename(uriResult.filename)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = uriResult.uri,
                        contentDescription = "Full Image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Text(
                        text = date,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(
                                Color.Black.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

fun extractDateFromFilename(filename: String): String {
    return try {
        // Regex to match the pattern IMG_yyyyMMdd_HHmmss.jpg
        val pattern = Regex("IMG_(\\d{8}_\\d{6})\\.jpg")
        val matchResult = pattern.find(filename)
        matchResult?.groupValues?.get(1)?.let { dateStr ->
            val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US)
            val date = dateFormat.parse(dateStr)
            val formattedDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date)
            formattedDate
        } ?: "Unknown Date"
    } catch (_: Exception) {
        "Unknown Date"
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