package com.muni.taskmajster.view.ui.theme.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp

fun customPageContentPaddingValues (
    innerPadding: PaddingValues
): PaddingValues {
    return PaddingValues(
        start = 20.dp,
        top = innerPadding.calculateTopPadding(),
        end = 20.dp,
        bottom = innerPadding.calculateBottomPadding()
    )
}