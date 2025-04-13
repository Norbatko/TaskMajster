package com.muni.taskmajster.ui.add_players_page.colorpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.muni.taskmajster.ui.components.button.ButtonIcon
import com.muni.taskmajster.ui.components.button.LargeButton

val colors = listOf(
    0xFFFFB3BA.toInt(), // Light Pink:   (255, 179, 186)
    0xFFFFDFBA.toInt(), // Light Orange: (255, 223, 186)
    0xFFFFFFBA.toInt(), // Light Yellow: (255, 255, 186)
    0xFFBAFFC9.toInt(), // Light Green:  (186, 255, 201)
    0xFFBAE1FF.toInt(), // Light Blue:   (186, 225, 255)
    0xFFDABAFF.toInt(), // Light Purple: (218, 186, 255)
    0xFFFFCCE5.toInt(), // Pink Blush:   (255, 204, 229)
    0xFFCCFFE5.toInt(), // Mint:         (204, 255, 229)
    0xFFCCE5FF.toInt(), // Baby Blue:    (204, 229, 255)
    0xFFE5CCFF.toInt(), // Lavender:     (229, 204, 255)
    0xFFFFFFFF.toInt(), // White:        (255, 255, 255)
    0xFFF0F0F0.toInt()  // Very Light Gray: (240, 240, 240)
)


@Composable
fun Colorpicker(
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            ColorpickerContent(
                colors = colors,
                onConfirm = onConfirm
            )
        }
    }
}

@Composable
fun ColorpickerContent(
    colors: List<Int>,
    onConfirm: (Int) -> Unit
) {
    val pickedColor = remember { mutableIntStateOf(250) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(colors.size) { index ->
                val color = colors[index]
                ColorChip(
                    color = color,
                    onClicked = { pickedColor.intValue = color },
                    isSelected = color == pickedColor.intValue
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LargeButton(
            text = "OK",
            icon = ButtonIcon.Vector(Icons.Default.Check),
            onClicked = { onConfirm(pickedColor.intValue) },
        )
    }
}

@Preview
@Composable
fun ColorPickerPreview(){
    Colorpicker(
        onConfirm = {},
        onDismiss = {}
    )
}