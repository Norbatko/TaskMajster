package com.muni.taskmajster.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Long,
    var currentTask: Int,
    val listOfPlayers: List<Player>,
    val gameplan: Gameplan
): Parcelable
