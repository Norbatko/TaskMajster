package com.muni.taskmajster.ui.gameplan_detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muni.taskmajster.ui.list_of_tasks.TaskItem
import com.muni.taskmajster.ui.task_detail.TaskButton

@Composable
fun GameplanDetail(
    onArrowBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            GameplanDetailToolbar(
                title = "Name of plan",
                onArrowBackClick = onArrowBackClick,
                onDeleteClick = {}
            )
        },
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Spacer(Modifier.height(30.dp))
            }

            item {
                TaskButton(
                    text = "Play Now",
                    icon = Icons.Outlined.PlayArrow,
                    onClick = { }
                )
            }

            item {
                Spacer(Modifier.height(30.dp))
            }

            item {
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
            }

            items(5) {index ->
                TaskItem(onTaskClick = {})
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(50.dp)
                    )

                    Text(
                        text = "Add new task",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                    )
                }
            }
        }
    }

}

@Composable
fun GameplanDetailToolbar(title: String, onArrowBackClick: () -> Unit, onDeleteClick: () -> Unit) {
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

        IconButton(onClick = onDeleteClick) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete"
            )
        }
    }
}

@Preview
@Composable
fun GameplanDetailPreview() {
    GameplanDetail (
        onArrowBackClick = {}
    )
}