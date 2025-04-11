package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Long,
    val name: String,
    val colour: Int, // todo may be changed in future acording to colour picker
    var totalPoints: Int,
    var taskPoints: Int,
): Parcelable
