package com.muni.taskmajster.view.ui.components.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.view.ui.components.common.CustomContainer
import com.muni.taskmajster.view.ui.components.common.CustomHorizontalDivider

@Composable
fun GameplanItem(
    gameplan: Gameplan,
    onGameplanClicked: (Gameplan) -> Unit = {},

    addToGameplan: Boolean = false,
    onAddToGameplan: () -> Unit = {},
) {
    val clickableModifier = if (!addToGameplan || onGameplanClicked == {}) {
        Modifier.clickable { onGameplanClicked(gameplan) }
    } else Modifier


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(clickableModifier)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Gameplan: " + gameplan.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = gameplan.listOfTaskIds.size.toString(),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }

            if (addToGameplan) {
                IconButton(
                    onClick = { onAddToGameplan() }
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add to gameplan"
                    )
                }
            }
        }
        CustomHorizontalDivider()
    }

}

@Preview
@Composable
fun GameplanItemPreview() {
    GameplanItem(
        gameplan = Gameplan("1", "THE gameplan", emptyList()),
        onGameplanClicked = {},
        addToGameplan = true
    )
}