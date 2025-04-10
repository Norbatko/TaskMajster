package com.muni.taskmajster.repository

import com.muni.taskmajster.data.Game
import com.muni.taskmajster.data.Task
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.data.Player

class TaskMajsterRepository {

    fun getFakeTasks(): List<Task> {
        return List(50) { index ->
            Task(
                id = index.toLong(),
                name = "Task $index",
                time = (10..120).random(),
                description = "Description for Task $index.",
                images = emptyList()
            )
        }
    }

    fun getFakeGameplans(): List<Gameplan> {
        val allTasks = getFakeTasks()

        return List(10) { index ->
            val tasks = allTasks.shuffled().take((0..10).random())

            Gameplan(
                id = index.toLong(),
                name = "Gameplan $index",
                listOfTasks = tasks
            )
        }
    }

    fun getFakePlayers(): List<Player> {
        return List(3) { index ->
            Player(
                id = index.toLong(),
                name = "Player $index",
                colour = index,
                totalPoints = 0,
                taskPoints = 0,
            )
        }
    }

    fun getFakeGame(): Game {
        val listOfPlayers = getFakePlayers()
        val gameplan = getFakeGameplans().first()

        return Game(
            id = 1,
            currentTask = 0,
            listOfPlayers = listOfPlayers,
            gameplan = gameplan,
        )
    }
}
