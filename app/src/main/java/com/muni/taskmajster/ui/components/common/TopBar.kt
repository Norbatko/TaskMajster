package com.muni.taskmajster.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

data class TopBarButton(
    val onClicked: () -> Unit,
    val icon: ImageVector,
    val contentDescription: String
)

@Composable
fun TopBar(
    title: String,
    onArrowBackClicked: (() -> Unit)? = null, // null means no back button
    sideButtons: List<TopBarButton> = emptyList()
) {
    Row(
        Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onArrowBackClicked != null) {
            IconButton(onClick = onArrowBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        } else {
            // placeholder to preserve size
            IconButton(onClick = {}, enabled = false, modifier = Modifier.alpha(0f)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.weight(1f))

        Row {
            sideButtons.forEach { button ->
                TopBarSideButton(
                    onClicked = button.onClicked,
                    icon = button.icon,
                    contentDescription = button.contentDescription
                )
            }
        }
    }
}

@Composable
fun TopBarSideButton(
    onClicked: () -> Unit,
    icon: ImageVector,
    contentDescription: String
) {
    IconButton(onClick = onClicked) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun TopBarPreview() {
    TopBar(
        title  = "TopBar",
        onArrowBackClicked = {},
        sideButtons = listOf(
            TopBarButton(onClicked = { }, icon = Icons.Default.Edit, contentDescription = "Edit"),
        )
    )
}