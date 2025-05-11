package com.muni.taskmajster.view.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun customBorder(width: Dp = 2.dp): BorderStroke {
    return BorderStroke(width = width, color = MaterialTheme.colorScheme.onBackground)
}