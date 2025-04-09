package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var id: Long,
    val name: String,
    val colour: Int, // todo may be changed in future acording to colour picker
    val totalPoints: Int,
    val taskPoints: Int,
): Parcelable
