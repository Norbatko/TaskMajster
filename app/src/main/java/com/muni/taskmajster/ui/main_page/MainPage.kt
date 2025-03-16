package com.muni.taskmajster.ui.main_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainPage () {
        Scaffold(
            topBar = {
                SpaceToolbar(
                    title = "Main Page"
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MenuButton(
                    text = "Play random",
                    icon = Icons.Default.Refresh,
                    onClick = { }
                )
                MenuButton(
                    text = "Play gameplan",
                    icon = Icons.Default.PlayArrow,
                    onClick = { }
                )
                MenuButton(
                    text = "Tasks",
                    icon = Icons.Default.Menu,
                    onClick = { }
                )
                MenuButton(
                    text = "Tasks",
                    icon = Icons.AutoMirrored.Filled.List,
                    onClick = { }
                )
            }
        }
}

@Composable
fun SpaceToolbar(
    title: String,
) {
    Row {
        IconButton(
            onClick = {},
            content = {
                //Icon(
                //    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                //    contentDescription = null
                //)
            }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    bottom = 16.dp,
                )
        )
    }
}

@Composable
fun MenuButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Preview
@Composable
fun MainPagePreview() {
    MainPage()
}