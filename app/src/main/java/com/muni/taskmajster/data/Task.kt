package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: Long,
    val name: String,
    val time: Int,
    val description: String,
    val images: List<Long>
): Parcelable
