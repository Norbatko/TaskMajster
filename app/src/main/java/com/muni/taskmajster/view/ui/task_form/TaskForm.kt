package com.muni.taskmajster.view.ui.task_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.common.TopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.components.button.LargeButton

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

    val isNameValid = name.isNotBlank()
    val isDescriptionValid = description.isNotBlank()
    val isTimeValid = time.toIntOrNull() != null

    var nameTouched by remember { mutableStateOf(false) }
    var descriptionTouched by remember { mutableStateOf(false) }

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
                singleLine = true,
                onValueChange = {
                    name = it
                    if (!nameTouched) nameTouched = true
                },
                label = { Text("Task Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameTouched && !isNameValid,
                supportingText = {
                    if (nameTouched && !isNameValid) {
                        Text("Name cannot be empty", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    if (!descriptionTouched) descriptionTouched = true
                },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                isError = descriptionTouched && !isDescriptionValid,
                supportingText = {
                    if (descriptionTouched && !isDescriptionValid) {
                        Text("Description cannot be empty", color = MaterialTheme.colorScheme.error)
                    }
                }
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = time,
                singleLine = true,
                onValueChange = { time = it },
                label = { Text("Time (seconds)") },
                modifier = Modifier.fillMaxWidth(),
                isError = !isTimeValid,
                supportingText = {
                    if (!isTimeValid) {
                        Text(
                            text = "Please enter a valid full number",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(24.dp))

                LargeButton(
                    text = if (isEditMode) "Update Task" else "Create Task",
                    onClicked = {
                        if (isNameValid && isDescriptionValid && isTimeValid) {
                            val task = Task(
                                id = initialTask?.id ?: "",
                                name = name,
                                description = description,
                                time = time.toInt(),
                                imagePaths = emptyList()
                            )
                            onSave(task)
                        }
                    },
                    transparent = false,
                    enabled = isNameValid && isDescriptionValid && isTimeValid
                )
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
