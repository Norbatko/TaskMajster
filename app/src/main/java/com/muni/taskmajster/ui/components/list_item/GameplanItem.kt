package com.muni.taskmajster.ui.components.list_item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.muni.taskmajster.data.Gameplan


@Composable
fun GameplanItem(
    gameplan: Gameplan,
    onGameplanClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = onGameplanClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = gameplan.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = gameplan.listOfTasks.size.toString(),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun GameplanItemPreview() {
    GameplanItem(
        gameplan = Gameplan(1, "THE gameplan", emptyList()),
        onGameplanClick = {})
}