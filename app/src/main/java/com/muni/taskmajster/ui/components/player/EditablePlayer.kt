package com.muni.taskmajster.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditablePlayer(
    name: String,
    onNameChanged: (String) -> Unit,
    color: Int
) {
    Row (
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(
            color = color,
            onClicked = {}
        )
        OutlinedTextField(
            modifier = Modifier.padding(6.dp),
            value = name,
            onValueChange = onNameChanged,
        )
    }

}