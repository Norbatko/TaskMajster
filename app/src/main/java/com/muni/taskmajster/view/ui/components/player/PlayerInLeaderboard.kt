package com.muni.taskmajster.view.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.model.data.Player

@Composable
fun PlayerInLeaderboard(
    player: Player
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(player.colour)
        Text(
            text = player.name,
            modifier = Modifier
                .padding(start = 6.dp)
                .widthIn(max = 150.dp)
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Text(player.totalPoints.toString(), style = MaterialTheme.typography.titleLarge)
    }
}