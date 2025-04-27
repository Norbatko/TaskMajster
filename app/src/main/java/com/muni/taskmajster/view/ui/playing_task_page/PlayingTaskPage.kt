package com.muni.taskmajster.view.ui.playing_task_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Player
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.common.TopBarButton
import com.muni.taskmajster.view.ui.playing_task_page.scoring_bottom_sheet.ScoringBottomSheet
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayingTaskPage(
    game: Game,
    listOfGameplanTasks: List<Task>,
    onDoneClicked: (Game) -> Unit,
    onArrowBackClicked: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 128.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            ScoringBottomSheet(game = game)
        },
        topBar = {
            TopBar(
                title = listOfGameplanTasks[game.currentTask].name,
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = listOf(
                    TopBarButton(
                        onClicked = { onDoneClicked(game) },
                        icon = Icons.Default.Check,
                        contentDescription = "Done"
                    ),
                )
            )
        },
    ) {
    PlayingTaskContent(description = listOfGameplanTasks[game.currentTask].description)
    }
}

@Composable
fun PlayingTaskContent(
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Text(
            description,
            style = MaterialTheme.typography.bodyLarge
        )
        LargeButton(
            "Play",
            ButtonIcon.Vector(Icons.Default.PlayArrow),
            onClicked = {})
    }
}


@Preview
@Composable
fun PlayingTaskPagePreview() {
    PlayingTaskPage(
        game = Game(
            1,
            0,
            listOfPlayers = List(8) { index ->
                Player(
                    id = index.toLong(),
                    name = "Player $index",
                    colour = Random.nextInt(),
                    taskPoints = (0..5).random(),
                    totalPoints = 0,
                )
            },
            gameplan = Gameplan(
                "1",
                "The gameplan",
                listOfTaskIds = List(1){
                    "1"
                }),
        ),
        listOfGameplanTasks = List(1){
            Task("1", "taskName", 20, "taskDescription", emptyList())
        },
        onDoneClicked = {},
        onArrowBackClicked = {}
    )
}