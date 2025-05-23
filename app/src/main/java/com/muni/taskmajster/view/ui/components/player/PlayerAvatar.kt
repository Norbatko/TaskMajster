package com.muni.taskmajster.view.ui.components.player

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
import com.muni.taskmajster.view.ui.components.common.customBorder

@Composable
fun PlayerAvatar(
    color: Int,
    onClicked: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(color))
            .border(customBorder(), CircleShape)
            .let { if (onClicked != null) it.clickable { onClicked() } else it }
    )
}