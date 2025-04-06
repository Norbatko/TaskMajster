package com.muni.taskmajster.ui.add_players_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@Composable
fun AddPlayersPage(
    onArrowBackClicked: () -> Unit
) {
    val players = remember { mutableStateListOf("") }

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
            onPlayerNameChanged = { index, newName -> players[index] = newName },
            onAddPlayer = { players.add("") },
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
    players: List<String>,
    onPlayerNameChanged: (Int, String) -> Unit,
    onAddPlayer: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding)

    ) {
        ListOfPlayers(
            players = players,
            onPlayerNameChanged = onPlayerNameChanged
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
    players: List<String>,
    onPlayerNameChanged: (Int, String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(players.count()) { index ->
            EditablePlayer(
                name = players[index],
                onNameChanged = { newName -> onPlayerNameChanged(index, newName) }
            )
        }
    }
}

@Preview
@Composable
fun AddPlayersPagePreview() {
    AddPlayersPage (
        onArrowBackClicked = {}
    )
}