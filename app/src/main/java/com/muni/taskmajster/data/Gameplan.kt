package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gameplan(
    val id: String,
    val name: String,
    var listOfTasks: List<Task>
): Parcelable
