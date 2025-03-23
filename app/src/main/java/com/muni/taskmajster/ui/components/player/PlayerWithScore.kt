package com.muni.taskmajster.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PlayerWithScore(
    name: String,
    color: Int,
    numberOfPoints: Int,
    showScoreSetter: Boolean
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(color)
        Text(name, modifier = Modifier.padding(6.dp) )

        Spacer(modifier = Modifier.weight(0.5f))
        Text(numberOfPoints.toString(), style = MaterialTheme.typography.titleLarge)

        if (showScoreSetter) ScoreSetter()
    }
}

@Composable
fun ScoreSetter() {
    Row (
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

@Preview(showBackground = true)
@Composable
fun PlayerWithScorePreview() {
    PlayerWithScore(
        name = "Player 1",
        color = 250,
        numberOfPoints = 8,
        showScoreSetter = true
    )
}