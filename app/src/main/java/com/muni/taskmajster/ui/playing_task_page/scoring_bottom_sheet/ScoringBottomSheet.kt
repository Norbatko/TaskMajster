package com.muni.taskmajster.ui.playing_task_page.scoring_bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ScoringBottomSheet() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(5) {
            PlayerScoreSetter()
        }
    }
}

@Composable
fun PlayerScoreSetter() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.Red)
        )

        Text("Player 1", modifier = Modifier.padding(6.dp) )
        Spacer(modifier = Modifier.weight(1f))
        Text("10", style = MaterialTheme.typography.titleLarge)
        IconButton(
            onClick = {
                {}
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        )
        IconButton(
            onClick = {
                {}
            },
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ScoringBottomSheetPreview() {
    ScoringBottomSheet()
}

