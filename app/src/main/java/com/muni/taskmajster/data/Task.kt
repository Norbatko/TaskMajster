package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String = "",
    val name: String = "",
    val time: Int = 0,
    val description: String = "",
    val imagePaths: List<String> = emptyList<String>()
): Parcelable
