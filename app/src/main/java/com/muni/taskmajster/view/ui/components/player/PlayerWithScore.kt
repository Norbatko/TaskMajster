package com.muni.taskmajster.view.ui.components.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.R
import com.muni.taskmajster.model.data.Player
import kotlin.random.Random

@Composable
fun PlayerWithScore(
    player: Player,
    score: Int,
    onScoreChanged: (Int) -> Unit,
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

        Text(score.toString(), style = MaterialTheme.typography.titleLarge)
        ScoreSetter(
            onPointRemoved = { onScoreChanged(-1) },
            onPointAdded = { onScoreChanged(+1) }
        )
    }
}

@Composable
fun ScoreSetter(
    onPointAdded: () -> Unit,
    onPointRemoved: () -> Unit
) {
    Row (
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPointRemoved,
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = null
                )
            }
        )
        IconButton(
            onClick = onPointAdded,
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
//        showScoreSetter = true,
        score = 10,
        onScoreChanged = {}
    )
}