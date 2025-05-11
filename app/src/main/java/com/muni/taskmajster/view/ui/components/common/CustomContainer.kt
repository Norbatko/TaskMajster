package com.muni.taskmajster.view.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.components.common.customBorder

@Composable
fun CustomContainer(
    contentPadding: PaddingValues = PaddingValues(20.dp),
    border: BorderStroke = customBorder(),
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        border = border,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(
            modifier = Modifier
                .padding(contentPadding)
        ) {
            content()
        }
    }
}
