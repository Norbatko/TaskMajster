package com.muni.taskmajster.view.ui.components.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.common.CustomHorizontalDivider

@Composable
fun TaskItem(
    task: Task,
    onTaskClicked: ((Task) -> Unit)? = null,
    // for displaying task item in list for adding to gameplan
    addToGameplan: Boolean = false,
    onAddToGameplanClicked: () -> Unit = {},
    // for displaying task item as part of gameplan (option to remove)
    inGameplanInfo: Boolean = false,
    onRemoveFromGameplanClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .let { base ->
                // if item is in add task to gameplan list its not clickable (it would be too disruptive)
                if (!addToGameplan || onTaskClicked != null) {
                    base.clickable { onTaskClicked?.invoke(task) }
                } else base
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Task: " + task.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.id_clock_outline),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "${task.time}s",
                        style = MaterialTheme.typography.bodyMedium
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
        CustomHorizontalDivider()
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