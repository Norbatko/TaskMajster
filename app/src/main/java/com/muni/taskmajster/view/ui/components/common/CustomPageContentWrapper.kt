package com.muni.taskmajster.view.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muni.taskmajster.view.ui.theme.util.customPageContentPaddingValues

@Composable
fun CustomPageContentWrapper(
    innerPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = customPageContentPaddingValues(innerPadding))
    ) {
        content()
    }
}
