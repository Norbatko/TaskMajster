package com.muni.taskmajster.view.ui.game.playing_task_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
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
import com.muni.taskmajster.view.ui.game.playing_task_page.scoring_bottom_sheet.ScoringBottomSheet
import kotlin.random.Random
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton

val bottomSheetPeekHeight = 128.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayingTaskPage(
    game: Game,
    listOfGameplanTasks: List<Task>,
    onDoneClicked: (Game) -> Unit,
    onArrowBackClicked: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val players = remember { mutableStateListOf<Player>().apply { addAll(game.listOfPlayers) } }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = bottomSheetPeekHeight,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            ScoringBottomSheet(
                listOfPlayers = players,
                onScoreChanged = { id, delta ->
                    val index = players.indexOfFirst { it.id == id }
                    if (index != -1) {
                        val player = players[index]
                        players[index] = player.copy(taskPoints = player.taskPoints + delta)
                    }
                }
            )
        },
        topBar = {
            TopBar(
                title = listOfGameplanTasks[game.currentTask].name,
                onArrowBackClicked = onArrowBackClicked,
                sideButtons = listOf(
                    TopBarButton(
                        onClicked = {
                            val updatedPlayers = players.map { player ->
                                player.copy(
                                    totalPoints = player.totalPoints + player.taskPoints,
                                    taskPoints = 0
                                )
                            }
                            val updatedGame = game.copy(listOfPlayers = updatedPlayers)
                            onDoneClicked(updatedGame)
                        },
                        icon = Icons.Default.Check,
                        contentDescription = "Done"
                    ),
                )
            )
        },
    ) {
    PlayingTaskContent(
        description = listOfGameplanTasks[game.currentTask].description,
        taskTime = listOfGameplanTasks[game.currentTask].time)
    }
}

@Composable
fun PlayingTaskContent(
    description: String,
    taskTime: Int,
) {
    var timeLeft by remember { mutableIntStateOf(taskTime) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isTimerRunning) {
        if (isTimerRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            if (timeLeft == 0) {
                isTimerRunning = false
                showDialog = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row (
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            LargeButton(
                text = if (isTimerRunning) "Restart" else "Play",
                icon = if (isTimerRunning)
                    ButtonIcon.Vector(Icons.Default.Refresh)
                else ButtonIcon.Vector(Icons.Default.PlayArrow),
                onClicked = {
                    timeLeft = taskTime
                    isTimerRunning = true
                },
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Time Left: $timeLeft s",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Scrollable description (prevents cut off by bottom sheet)
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(bottom = bottomSheetPeekHeight)
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    // TODO move elsewhere and use also for delte confirm
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Time is up!") },
            text = { Text("The timer has finished.") },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
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
            Task("1",
                "taskName",
                10,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam rhoncus consectetur ligula nec pretium. Suspendisse quis nulla quam. Duis a commodo dui. Ut molestie erat vitae rutrum maximus. Suspendisse potenti. Cras pellentesque enim sed augue porta laoreet. Curabitur eget augue quis lorem fringilla elementum in sit amet tellus. Cras sagittis pulvinar tellus blandit ullamcorper. Fusce ornare ultricies dapibus. Vestibulum blandit nec augue eget feugiat. Aliquam erat volutpat. Sed quis justo facilisis, interdum tellus vitae, ornare leo.\n" +
                "\n" +
                "Vivamus accumsan faucibus nibh, sed placerat felis consectetur vel. Aliquam id diam dui. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Praesent id turpis quis sem volutpat pharetra. Nullam a lectus eget mauris dignissim tincidunt. Praesent tellus tellus, aliquet vitae accumsan non, tempus sed lectus. Fusce sed tortor vel quam suscipit lobortis at ac nisl. Quisque facilisis risus quis hendrerit pulvinar. Maecenas euismod, erat et rhoncus pharetra, enim arcu consequat magna, at aliquet ligula nisl id est. Sed suscipit lorem nulla, quis accumsan diam facilisis eu. Curabitur ornare, nisi in viverra venenatis, nunc velit pharetra lorem, non sagittis enim quam at eros. Maecenas vitae pharetra diam. Aenean et hendrerit libero. Donec sodales mauris id elit ultricies, quis venenatis sapien pharetra. Curabitur sapien urna, viverra porttitor consequat ac, tristique in sapien. Pellentesque gravida erat id leo aliquet vestibulum.",
                emptyList())
        },
        onDoneClicked = {},
        onArrowBackClicked = {}
    )
}