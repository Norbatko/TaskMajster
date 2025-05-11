package com.muni.taskmajster.view.ui.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomAlertDialog(
    title: String,
    description: String,
    confirmText: String,
    onConfirmClicked: () -> Unit,
    onDismiss: () -> Unit,
    showCancel: Boolean
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = { Text(description) },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                    onConfirmClicked()
                },
            ) {
                Text(confirmText)
            }
        },
        dismissButton = if (showCancel) {
            {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        } else null
    )
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    CustomAlertDialog(
        title = "Delete Task",
        description = "Do you really want to delete this task?",
        confirmText = "Delete",
        onConfirmClicked = {},
        onDismiss = { },
        showCancel = true
    )
}