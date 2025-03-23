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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import java.io.File

val LoremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus auctor accumsan mauris, at consectetur urna porttitor eu. Pellentesque nulla tellus, semper eu ex id, mollis vestibulum ante. Vestibulum sit amet magna at odio consequat feugiat sit amet ut magna. Etiam congue sapien non eleifend fringilla. Nullam sit amet nisl at justo varius ullamcorper eu ac risus. Pellentesque in consequat neque. Nulla auctor erat id posuere tristique. Aliquam cursus ipsum mi. Integer gravida orci ac sem egestas, vel lobortis libero iaculis. Aenean viverra dictum metus, eget volutpat turpis sodales at. Pellentesque odio magna, aliquet a euismod et, laoreet sit amet massa. Aliquam non mattis orci. Donec ornare at neque quis sodales. Proin at volutpat orci.)"

@Composable
fun TaskDetail(
    onArrowBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TaskDetailToolbar(
                title = "Task: Name",
                onArrowBackClick = onArrowBackClick,
                onEditClick = {},
                onDeleteClick = {}
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
                TaskButton(
                    text = "Play Now",
                    icon = Icons.Outlined.PlayArrow,
                    onClick = { }
                )

                Spacer(modifier = Modifier.size(16.dp)) // Space between buttons

                TaskButton(
                    text = "Add to plan",
                    icon = Icons.AutoMirrored.Default.List,
                    onClick = { }
                )
            }
            Text(
                text = LoremIpsum,
            )

            Spacer(modifier = Modifier.size(32.dp))

            Text(
                text = "Time to complete: XXX seconds",
                modifier = Modifier.padding(10.dp)
            )

            HorizontalDivider(thickness = 2.dp, color = Color.Black)

            Text(
                text = "Photos from previous games",
                fontSize = 25.sp,
                modifier = Modifier.padding(20.dp))

            PhotoGrid((1..13).toList())

        }
    }
}

@Composable
fun TaskDetailToolbar(title: String, onArrowBackClick: () -> Unit, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onArrowBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Row {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Composable
fun TaskButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
            Spacer(Modifier.width(10.dp))
            Text(text = text)
        }
    }
}

@Composable
fun PhotoGrid(photoList: List<Int>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val file = File("/storage/emulated/0/Pictures/IMG_20250322_193456.jpg")

        val rows = photoList.chunked(3)  // Group images into rows of 3
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
    TaskDetail(onArrowBackClick = {})
}