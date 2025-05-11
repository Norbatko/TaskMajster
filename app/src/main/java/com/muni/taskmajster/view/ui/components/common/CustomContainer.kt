package com.muni.taskmajster.view.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muni.taskmajster.view.ui.theme.customBorder

@Composable
fun CustomContainer(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    border: BorderStroke = customBorder(),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        border = border,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            content()
        }
    }
}
