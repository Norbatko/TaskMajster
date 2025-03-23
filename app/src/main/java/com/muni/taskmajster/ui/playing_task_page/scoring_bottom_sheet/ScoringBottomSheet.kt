package com.muni.taskmajster.ui.playing_task_page.scoring_bottom_sheet

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.ui.components.player.PlayerWithScore
import kotlin.random.Random


@Composable
fun ScoringBottomSheet() {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(5) {
            PlayerWithScore(
                name = "Player ${it + 1}",
                color = Random.nextInt(),
                numberOfPoints = Random.nextInt(5, 10),
                showScoreSetter = true
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScoringBottomSheetPreview() {
    ScoringBottomSheet()
}

