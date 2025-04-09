package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gameplan(
    var id: Long,
    val name: String,
    val listOfTasks: List<Task>
): Parcelable
