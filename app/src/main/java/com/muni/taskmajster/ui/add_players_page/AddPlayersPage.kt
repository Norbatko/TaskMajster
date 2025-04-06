package com.muni.taskmajster.ui.add_players_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.ui.components.common.PlayButton
import com.muni.taskmajster.ui.components.player.EditablePlayer
import kotlin.random.Random

@Composable
fun AddPlayersPage(
    onArrowBackClicked: () -> Unit,
    onPlayClicked: () -> Unit
) {
    val players = remember { mutableStateListOf("" to Random.nextInt()) }

    Scaffold(
        topBar = {
            AddPlayersPageTopBar(
                title = "Add players",
                onArrowBackClicked = onArrowBackClicked
            )
        },
    ) { innerPadding ->
        AddPlayersPageContent(
            padding = innerPadding,
            players = players,
            onPlayerNameChanged = { index, newName ->
                players[index] = newName to players[index].second
            },
            onAddPlayer = {
                players.add("" to Random.nextInt())
            },
            onPlayerRemoved = { index -> players.removeAt(index) }
        )
    }
}

@Composable
fun AddPlayersPageTopBar(
    title: String,
    onArrowBackClicked: () -> Unit
) {
    Row (
        Modifier
            .background(color = Color.LightGray)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onArrowBackClicked,
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
        )
    }
}

@Composable
fun AddPlayersPageContent(
    padding: PaddingValues,
    players: List<Pair<String, Int>>,
    onPlayerNameChanged: (Int, String) -> Unit,
    onAddPlayer: () -> Unit,
    onPlayerRemoved: (Int) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding),

    ) {
        ListOfPlayers(
            players = players,
            onPlayerNameChanged = onPlayerNameChanged,
            onPlayerRemoved = onPlayerRemoved
        )
        IconButton(
            onClick = onAddPlayer,
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add Player"
                )
            }
        )
        Spacer(modifier = Modifier.weight(0.5f))
        PlayButton(onClick = {})
    }
}

@Composable
fun ListOfPlayers(
    players: List<Pair<String, Int>>,
    onPlayerNameChanged: (Int, String) -> Unit,
    onPlayerRemoved: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(players.count()) { index ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                EditablePlayer(
                    name = players[index].first,
                    color = players[index].second,
                    onNameChanged = { newName -> onPlayerNameChanged(index, newName) }
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {onPlayerRemoved(index)},
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
}

@Preview
@Composable
fun AddPlayersPagePreview() {
    AddPlayersPage (
        onArrowBackClicked = {},
        onPlayClicked = {}
    )
}