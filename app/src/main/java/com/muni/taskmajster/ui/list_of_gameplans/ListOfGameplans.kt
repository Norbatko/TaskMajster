package com.muni.taskmajster.ui.list_of_gameplans

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListOfGameplans(
    onArrowBackClicked: () -> Unit
) {
    print("JSEM TU")
    Scaffold(
        topBar = {
            TopBar(
                title = "List of gameplans",
                onArrowBackClicked = onArrowBackClicked
            )
        },
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            items(15) { index ->
                GameplanItem()
            }
        }
    }
}

@Composable
fun TopBar(
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
            onClick = {onArrowBackClicked},
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
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {},
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            },
            modifier = Modifier.background(color = Color.Gray),
        )
    }
}

@Composable
fun GameplanItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable(onClick = {}),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "Name of gameplan",
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "X",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 4.dp, end = 4.dp),
            thickness = 1.dp,
            color = Color.DarkGray
        )
    }
}


@Preview
@Composable
fun ListOfGameplansPreview() {
    ListOfGameplans(
        onArrowBackClicked = {}
    )
}
