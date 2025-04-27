package com.muni.taskmajster.model.data

import android.os.Parcelable
import com.muni.taskmajster.util.PlayerColorProvider
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Long,
    val name: String,
    val colour: Int,
    var totalPoints: Int,
    var taskPoints: Int,
): Parcelable {
    companion object {
        fun createNew(): Player {
            return Player(
                id = System.currentTimeMillis(),
                name = "Player X",
                colour = PlayerColorProvider.nextColor(),
                totalPoints = 0,
                taskPoints = 0
            )
        }
    }
}
