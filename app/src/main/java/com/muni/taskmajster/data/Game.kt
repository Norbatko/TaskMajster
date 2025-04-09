package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    var id: Long,
    val currentTask: Int,
    val listOfPlayers: List<Player>,
    val gameplan: Gameplan // play random gets list of all tasks randomized
): Parcelable
