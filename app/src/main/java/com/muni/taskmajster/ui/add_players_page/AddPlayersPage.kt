package com.muni.taskmajster.ui.add_players_page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.data.Game
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.data.Player
import com.muni.taskmajster.data.Task
import com.muni.taskmajster.ui.add_players_page.colorpicker.Colorpicker
import com.muni.taskmajster.ui.components.button.ButtonIcon
import com.muni.taskmajster.ui.components.button.LargeButton
import com.muni.taskmajster.ui.components.common.TopBar
import com.muni.taskmajster.ui.components.player.EditablePlayer
import kotlin.random.Random

@Composable
fun AddPlayersPage(
    game: Game,
    onArrowBackClicked: () -> Unit,
    onPlayClicked: (Game) -> Unit
) {
    val players = remember { mutableStateListOf(Player.createNew()) }

    Scaffold(
        topBar = {
            TopBar(
                title = "Add players",
                onArrowBackClicked = onArrowBackClicked
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                LargeButton(
                    text = "Play",
                    icon = ButtonIcon.Vector(Icons.Default.PlayArrow),
                    onClicked = {
                        val updatedGame = game.copy(listOfPlayers = players.toList())
                        onPlayClicked(updatedGame)
                    }
                )
            }
        }
    ) { innerPadding ->
        AddPlayersPageContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            players = players,
            onPlayerNameChanged = { index, newName ->
                val player = players[index]
                players[index] = player.copy(name = newName)
            },
            onPlayerAvatarColorChanged = { index, newColor ->
                val player = players[index]
                players[index] = player.copy(colour = newColor)
            },
            onPlayerRemoved = { index ->
                if (players.size > 1) {
                    players.removeAt(index)
                }
            },
            onAddPlayer = {
                players.add(Player.createNew())
            }
        )
    }
}

@Composable
fun AddPlayersPageContent(
    modifier: Modifier = Modifier,
    players: List<Player>,
    onPlayerNameChanged: (Int, String) -> Unit,
    onPlayerAvatarColorChanged: (Int, Int) -> Unit,
    onPlayerRemoved: (Int) -> Unit,
    onAddPlayer: () -> Unit,
) {
    var showColorPicker by remember { mutableStateOf(false) }
    var clickedPlayerIndex by remember { mutableIntStateOf(-1) }

    if (showColorPicker) {
        Colorpicker(
            onConfirm = { newColor ->
                onPlayerAvatarColorChanged(clickedPlayerIndex, newColor)
                showColorPicker = false
            },
            onDismiss = {
                showColorPicker = false
            }
        )
    }

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        listOfPlayersItems(
            players = players,
            onPlayerNameChanged = onPlayerNameChanged,
            onPlayerRemoved = onPlayerRemoved,
            onPlayerAvatarClicked = { index ->
                clickedPlayerIndex = index
                showColorPicker = true
            }
        )
        item {
            IconButton(onClick = onAddPlayer) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add Player"
                )
            }
        }
    }
}

fun LazyListScope.listOfPlayersItems(
    players: List<Player>,
    onPlayerNameChanged: (Int, String) -> Unit,
    onPlayerRemoved: (Int) -> Unit,
    onPlayerAvatarClicked: (Int) -> Unit,
) {
    items(players.size) { index ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EditablePlayer(
                name = players[index].name,
                color = players[index].colour,
                onNameChanged = { newName -> onPlayerNameChanged(index, newName) },
                onAvatarClicked = { onPlayerAvatarClicked(index) } ,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { onPlayerRemoved(index) },
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove player"
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun AddPlayersPagePreview() {
    AddPlayersPage(
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
                )
            },
            gameplan = Gameplan(
                1,
                "The gameplan",
                listOfTasks = List(1){
                    Task(1, "taskName", 20, "taskDescription", emptyList())
                })
        ),
        onArrowBackClicked = {},
        onPlayClicked = {}
    )
}