package com.muni.taskmajster.view.ui.game.add_players_page.colorpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColorChip(
    color: Int,
    onClicked: () -> Unit,
    isSelected: Boolean
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(color))
            .border(4.dp, if (isSelected) Color.DarkGray else Color.White, CircleShape)
            .clickable { onClicked() }
    )
}