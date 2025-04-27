package com.muni.taskmajster.view.ui.playing_task_page.scoring_bottom_sheet

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Player
import com.muni.taskmajster.ui.components.player.PlayerWithScore
import kotlin.random.Random


@Composable
fun ScoringBottomSheet(
    game: Game,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(items = game.listOfPlayers, key = { it.id }) { player ->
            PlayerWithScore(
                player = player,
                showScoreSetter = true
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScoringBottomSheetPreview() {
    ScoringBottomSheet(
        game = Game(
            1,
            1,
            listOfPlayers = List(8) { index ->
            Player(
                id = index.toLong(),
                name = "Player $index",
                colour = Random.nextInt(),
                taskPoints = (0..5).random(),
                totalPoints = 0,
            )},
            gameplan = Gameplan(
                "1",
                "The gameplan",
                listOfTasks = emptyList())
            )
    )
}

