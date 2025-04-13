package com.muni.taskmajster.util

object PlayerColorProvider {
    private val colors = listOf(
        0xFFFFB3BA.toInt(),
        0xFFFFDFBA.toInt(),
        0xFFFFFFBA.toInt(),
        0xFFBAFFC9.toInt(),
        0xFFBAE1FF.toInt(),
        0xFFDABAFF.toInt(),
        0xFFFFCCE5.toInt(),
        0xFFCCFFE5.toInt(),
        0xFFCCE5FF.toInt(),
        0xFFE5CCFF.toInt(),
        0xFFFFFFFF.toInt(),
        0xFFF0F0F0.toInt()
    )

    private var currentIndex = 0

    fun nextColor(): Int {
        val color = colors[currentIndex % colors.size]
        currentIndex++
        return color
    }

    fun reset() {
        currentIndex = 0
    }

    fun getAll(): List<Int> = colors
}
