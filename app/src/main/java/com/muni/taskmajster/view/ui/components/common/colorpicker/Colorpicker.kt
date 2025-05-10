package com.muni.taskmajster.view.ui.components.common.colorpicker

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.util.PlayerColorProvider

@Composable
fun Colorpicker(
    colors: List<Int>,
    onConfirm: (Int) -> Unit,
    onDismiss: () -> Unit,
    initialPickedColor: Int
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
                onConfirm = onConfirm,
                initialPickedColor = initialPickedColor,
            )
        }
    }
}

@Composable
fun ColorpickerContent(
    colors: List<Int>,
    onConfirm: (Int) -> Unit,
    initialPickedColor: Int
) {
    var pickedColor by remember { mutableIntStateOf(initialPickedColor) }

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
                    onClicked = { pickedColor = color },
                    isSelected = color == pickedColor
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LargeButton(
            text = "OK",
            icon = ButtonIcon.Vector(Icons.Default.Check),
            onClicked = { onConfirm(pickedColor) },
        )
    }
}

@Preview
@Composable
fun ColorPickerPreview(){
    Colorpicker(
        onConfirm = {},
        onDismiss = {},
        colors = PlayerColorProvider.getAll(),
        initialPickedColor = 255
    )
}