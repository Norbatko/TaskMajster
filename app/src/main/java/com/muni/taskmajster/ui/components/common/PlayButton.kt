package com.muni.taskmajster.ui.components.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayButton (
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null
            )
            Text(
                "Play",
                style = MaterialTheme.typography.labelLarge)
        },
        modifier = Modifier.padding(20.dp)
    )
}