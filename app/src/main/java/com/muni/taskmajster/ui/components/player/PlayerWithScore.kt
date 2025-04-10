package com.muni.taskmajster.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.data.Player
import kotlin.random.Random

@Composable
fun PlayerWithScore(
    player: Player,
    showScoreSetter: Boolean
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(player.colour)
        Text(player.name, modifier = Modifier.padding(6.dp) )

        Spacer(modifier = Modifier.weight(0.5f))
        Text(player.taskPoints.toString(), style = MaterialTheme.typography.titleLarge)

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
            onClick = {},
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = null
                )
            }
        )
        IconButton(
            onClick = {},
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
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
        player = Player(1, "Player", Random.nextInt(), 2, 5),
        showScoreSetter = true
    )
}