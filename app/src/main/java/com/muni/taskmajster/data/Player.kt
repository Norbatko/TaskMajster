package com.muni.taskmajster.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class Player(
    val id: Long,
    val name: String,
    val colour: Int, // todo may be changed in future acording to colour picker
    var totalPoints: Int,
    var taskPoints: Int,
): Parcelable {
    companion object {
        fun createNew(): Player {
            return Player(
                id = System.currentTimeMillis(),
                name = "Player X",
                colour = Random.nextInt(),
                totalPoints = 0,
                taskPoints = 0
            )
        }
    }
}
