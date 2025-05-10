package com.muni.taskmajster.view.ui.game.end_of_task_page

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Player
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.util.CameraCaptureUtil
import com.muni.taskmajster.view.ui.components.button.ButtonIcon
import com.muni.taskmajster.view.ui.components.button.LargeButton
import com.muni.taskmajster.view.ui.components.common.TopBar
import com.muni.taskmajster.view.ui.components.player.PlayerInLeaderboard
import androidx.activity.compose.rememberLauncherForActivityResult
import com.muni.taskmajster.viewModel.EndOfTaskPageViewModel
import kotlin.random.Random

// TODO maybe somewhere display number of remaining tasks?
@Composable
fun EndOfTaskPage(
    game: Game,
    viewModel: EndOfTaskPageViewModel,
    listOfGameplanTasks: List<Task>,
    lastTask: Boolean = false,
    onArrowBackClicked: () -> Unit,
    onNextTaskClicked: (Game) -> Unit = {},
    onFinalizeClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = listOfGameplanTasks[game.currentTask].name,
                onArrowBackClicked = onArrowBackClicked
            )
        },
    ) { innerPadding ->
        EndOfTaskPageContent(game, viewModel, listOfGameplanTasks, lastTask, innerPadding, onFinalizeClicked, onNextTaskClicked)
    }
}

@Composable
fun EndOfTaskPageContent(
    game: Game,
    viewModel: EndOfTaskPageViewModel,
    listOfGameplanTasks: List<Task>,
    lastTask: Boolean,
    padding: PaddingValues,
    onFinalizeClicked: () -> Unit,
    onNextTaskClicked: (Game) -> Unit
) {
    val context = LocalContext.current
    val photoUri = remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            Toast.makeText(context, "Photo saved to gallery", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Camera cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        FinalScoreBoard(
            listOfPlayers = game.listOfPlayers,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LargeButton(
                "Take a photo",
                ButtonIcon.PainterIcon(painterResource(R.drawable.ic_add_a_photo)),
                onClicked = {
                    val result = CameraCaptureUtil.createImageUri(context)
                    if (result.uri != null) {
                        photoUri.value = result.uri
                        cameraLauncher.launch(result.uri)
                        var task = listOfGameplanTasks[game.currentTask]
                        val updatedTask = task.copy(imagePaths = task.imagePaths + result.fileName)
                        viewModel.updateTask(updatedTask)
                    }
                },
                true
            )
            if (!lastTask) {
                LargeButton(
                    "Next task",
                    ButtonIcon.Vector(Icons.Default.PlayArrow),
                    onClicked = { onNextTaskClicked(game) }
                )
            }
            LargeButton(
                "Finalize",
                ButtonIcon.Vector(Icons.Default.Check),
                onClicked = onFinalizeClicked,
            )
        }
    }
}

@Composable
fun FinalScoreBoard(
    listOfPlayers: List<Player>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listOfPlayers.sortedByDescending { it.totalPoints },
            key = { it.id }
        ) { player ->
            PlayerInLeaderboard(player)
        }
    }
}


@Preview
@Composable
fun EndOfTaskPagePreview() {
    EndOfTaskPage(
        game = Game(
            1,
            0,
            listOfPlayers = List(10) { index ->
                Player(
                    id = index.toLong(),
                    name = "Player $index",
                    colour = Random.nextInt(),
                    taskPoints = (0..5).random(),
                    totalPoints = (0..10).random(),
                )
            },
            gameplan = Gameplan(
                "1",
                "The gameplan",
                listOfTaskIds = List(1){
                    "1"
                })
        ),
        viewModel = EndOfTaskPageViewModel(),
        listOfGameplanTasks = List(1) {
            Task("1", "taskName", 20, "taskDescription", emptyList())
        },
        onArrowBackClicked = {},
        onFinalizeClicked = {},
        onNextTaskClicked = {}
    )
}