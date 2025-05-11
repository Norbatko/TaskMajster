package com.muni.taskmajster.view.ui.components.player

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun PlayerWithScoreSetter(
    player: Player,
    score: Int,
    onScoreChanged: (Int) -> Unit,
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerAvatar(player.colour)

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 6.dp, end = 10.dp)
                .widthIn(max = 150.dp)
        ) {
            Column (
            modifier = Modifier
                .padding(start = 6.dp),
            ){
                Text(
                    text = player.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Total: " + player.totalPoints.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
            ) }
        }

        IconButton(
            onClick = { onScoreChanged(-1) },
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = null
                )
            }
        )

        Text(score.toString(), style = MaterialTheme.typography.titleLarge)

        IconButton(
            onClick = { onScoreChanged(+1) },
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
    PlayerWithScoreSetter(
        player = Player(
            1,
            "Player ABC",
            Random.nextInt(),
            2,
            5
        ),
        score = 10,
        onScoreChanged = {}
    )
}