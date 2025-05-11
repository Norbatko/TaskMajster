package com.muni.taskmajster.view.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.theme.util.customBorder

// we can add vector picture or painter
sealed class ButtonIcon {
    data class Vector(val imageVector: ImageVector) : ButtonIcon()
    data class PainterIcon(val painter: androidx.compose.ui.graphics.painter.Painter) : ButtonIcon()
}

@Composable
fun LargeButton(
    text: String,
    icon: ButtonIcon? = null,
    onClicked: () -> Unit,
    transparent: Boolean = false,
    enabled: Boolean = true,
) {
    Row(
        modifier = Modifier.padding(vertical = 15.dp)
    ) {
        Button (
            onClick = onClicked,
            modifier = Modifier
                .height(70.dp),
            colors = if (transparent) {
                ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            } else {
                ButtonDefaults.buttonColors()
            },
            enabled = enabled,
            border = customBorder()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (icon) {
                    is ButtonIcon.Vector -> Icon(
                        imageVector = icon.imageVector,
                        contentDescription = null,
                    )
                    is ButtonIcon.PainterIcon -> Icon(
                        painter = icon.painter,
                        contentDescription = null
                    )
                    null -> {}
                }

                if (icon != null) {
                    Spacer(Modifier.width(10.dp))
                }

                Text(text)
            }
        }
    }
}

@Preview
@Composable
fun LargeButtonPreview() {
    LargeButton(
        "Button",
        icon = ButtonIcon.Vector(Icons.Outlined.PlayArrow),
        onClicked = {},
        true
    )
}