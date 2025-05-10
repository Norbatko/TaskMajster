package com.muni.taskmajster.view.ui.components.bottom_sheet.scoring_bottom_sheet

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.model.data.Player
import com.muni.taskmajster.view.ui.components.player.PlayerWithScoreSetter
import kotlin.random.Random


@Composable
fun ScoringBottomSheet(
    listOfPlayers: List<Player>,
    onScoreChanged: (playerId: Long, delta: Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(
                text = "Scores",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(items = listOfPlayers, key = { it.id }) { player ->
            PlayerWithScoreSetter(
                player = player,
                score = player.taskPoints,
                onScoreChanged = { delta -> onScoreChanged(player.id, delta) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScoringBottomSheetPreview() {
    ScoringBottomSheet(
        listOfPlayers = List(8) { index ->
            Player(
                id = index.toLong(),
                name = "Player $index",
                colour = Random.nextInt(),
                taskPoints = (0..5).random(),
                totalPoints = 0,
            )
        },
        onScoreChanged = {x, y -> Unit},
    )
}

