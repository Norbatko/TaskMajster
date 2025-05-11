package com.muni.taskmajster.view.ui.theme.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

fun customPageContentPaddingValues (
    innerPadding: PaddingValues
): PaddingValues {
    val padding = 20
    return PaddingValues(
        start = padding.dp,
        top = innerPadding.calculateTopPadding() + padding.dp,
        end = padding.dp,
        bottom = innerPadding.calculateBottomPadding() + padding.dp
    )
}