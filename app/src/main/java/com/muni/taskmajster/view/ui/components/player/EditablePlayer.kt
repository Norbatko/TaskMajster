package com.muni.taskmajster.view.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun EditablePlayer(
    name: String,
    onNameChanged: (String) -> Unit,
    onAvatarClicked: () -> Unit,
    color: Int,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(
            color = color,
            onClicked = onAvatarClicked
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(start = 6.dp)
                .weight(1f)
                .widthIn(max = 200.dp),
            value = name,
            onValueChange = onNameChanged,
            singleLine = true
        )
    }
}

@Preview
@Composable
fun EditablePlayerPreview() {
    EditablePlayer(
        name = "PLAYER",
        color = Random.nextInt(),
        onNameChanged = {},
        onAvatarClicked = {}
    )
}