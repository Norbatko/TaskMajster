package com.muni.taskmajster.view.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding

data class TopBarButton(
    val onClicked: () -> Unit,
    val icon: ImageVector,
    val contentDescription: String
)

@Composable
fun TopBar(
    title: String,
    onArrowBackClicked: (() -> Unit)? = null, // null = no back button
    sideButtons: List<TopBarButton> = emptyList()
) {
    Row(
        Modifier
            .padding(WindowInsets.statusBars.asPaddingValues())
            .background(color = MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onArrowBackClicked != null) {
            IconButton(onClick = onArrowBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onTertiary
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
            color = MaterialTheme.colorScheme.onTertiary
        )

        Spacer(modifier = Modifier.weight(1f))

        Row {
            sideButtons.forEach { button ->
                TopBarSideButton(
                    onClicked = button.onClicked,
                    icon = button.icon,
                    contentDescription = button.contentDescription,
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
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onTertiary
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