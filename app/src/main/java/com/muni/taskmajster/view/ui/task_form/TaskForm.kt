package com.muni.taskmajster.view.ui.task_form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.common.TopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskForm(
    initialTask: Task? = null,
    onSave: (Task) -> Unit,
    onCancel: () -> Unit,
    isEditMode: Boolean = false
) {
    var name by remember { mutableStateOf(initialTask?.name ?: "") }
    var description by remember { mutableStateOf(initialTask?.description ?: "") }
    var time by remember { mutableStateOf(initialTask?.time?.toString() ?: "60") }

    Scaffold(
        topBar = {
            TopBar(
                title = if (isEditMode) "Edit Task" else "Create Task",
                onArrowBackClicked = onCancel,
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Task Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Time (seconds)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(24.dp))

            Button(onClick = {
                val task = Task(
                    id = initialTask?.id ?: "", // "" for new task
                    name = name,
                    description = description,
                    time = time.toIntOrNull() ?: 60,
                    imagePaths = initialTask?.imagePaths ?: emptyList() // or add photo logic
                )
                onSave(task)
            }) {
                Text(text = if (isEditMode) "Update Task" else "Create Task")
            }
        }
    }
}

@Preview
@Composable
fun TaskFormPreview() {
    TaskForm(
        onCancel = {},
        onSave = {},
    )
}
