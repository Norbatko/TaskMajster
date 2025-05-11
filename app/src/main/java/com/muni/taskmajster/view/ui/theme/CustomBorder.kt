package com.muni.taskmajster.view.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun customBorder(width: Dp = 2.dp, color: Color = Color.Black): BorderStroke {
    return BorderStroke(width = width, color = color)
}
