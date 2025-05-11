package com.muni.taskmajster.view.ui.components.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muni.taskmajster.R
import com.muni.taskmajster.model.data.Task

@Composable
fun TaskItem(
    task: Task,
    onTaskClicked: (Task) -> Unit = {},
    // for displaying task item in list for adding to gameplan
    addToGameplan: Boolean = false,
    onAddToGameplanClicked: () -> Unit = {},
    // for displaying task item as part of gameplan (option to remove)
    inGameplanInfo: Boolean = false,
    onRemoveFromGameplanClicked: () -> Unit = {}
) {
    // if item is in add to gameplan list its not clickable (it would be too disruptive)
    val clickableModifier = if (!addToGameplan || onTaskClicked == {}) {
        Modifier.clickable { onTaskClicked(task) }
    } else Modifier

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { clickableModifier }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Task: " + task.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock, // TODO: Change to Clock
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "${task.time}s",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
            if (addToGameplan) {
                IconButton(onClick = { onAddToGameplanClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add to gameplan"
                    )
                }
            } else if (inGameplanInfo) {
                IconButton(onClick = { onRemoveFromGameplanClicked() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.remove_circle),
                        contentDescription = "Remove from gameplan"
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
    }
}


@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        task = Task("1", "task1", 15, "task description", emptyList()),
        onTaskClicked = {},
        //addToGameplan = true,
        inGameplanInfo = true
    )
}