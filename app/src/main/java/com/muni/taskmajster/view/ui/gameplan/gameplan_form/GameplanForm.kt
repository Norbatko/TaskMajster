package com.muni.taskmajster.view.ui.gameplan.gameplan_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.view.ui.components.common.TopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun GameplanForm(
    initialGameplan: Gameplan? = null,
    onSaveClicked: (Gameplan) -> Unit,
    onCancelClicked: () -> Unit,
    isEditMode: Boolean = false
) {
    var name by remember { mutableStateOf(initialGameplan?.name ?: "") }

    val isNameValid = name.isNotBlank()

    var nameTouched by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                title = if (isEditMode) "Edit Gameplan" else "Create Gameplan",
                onArrowBackClicked = onCancelClicked,
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
                label = { Text("Gameplan Name") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameTouched && !isNameValid,
                supportingText = {
                    if (nameTouched && !isNameValid) {
                        Text("Name cannot be empty", color = MaterialTheme.colorScheme.error)
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
                LargeButton(
                    text = if (isEditMode) "Update Gameplan" else "Create Gameplan",
                    onClicked = {
                        if (isNameValid) {
                            val task = Gameplan(
                                id = initialGameplan?.id ?: "",
                                name = name,
                                listOfTaskIds = emptyList(),
                            )
                            onSaveClicked(task)
                        }
                    },
                    transparent = false,
                    enabled = isNameValid
                )
            }
        }
    }
}

@Preview
@Composable
fun GameplanFormPreview() {
    GameplanForm(
        onCancelClicked = {},
        onSaveClicked = {},
    )
}
