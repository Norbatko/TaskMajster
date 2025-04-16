package com.muni.taskmajster.data

import android.os.Parcelable
import com.muni.taskmajster.util.PlayerColorProvider
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: String,
    val name: String,
    val colour: Int,
    var totalPoints: Int,
    var taskPoints: Int,
): Parcelable {
    companion object {
        fun createNew(): Player {
            return Player(
                id = System.currentTimeMillis().toString(),
                name = "Player X",
                colour = PlayerColorProvider.nextColor(),
                totalPoints = 0,
                taskPoints = 0
            )
        }
    }
}
