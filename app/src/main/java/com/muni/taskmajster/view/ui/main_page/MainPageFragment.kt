package com.muni.taskmajster.view.ui.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.model.data.Game
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.TaskRepository
import com.muni.taskmajster.view.ui.theme.AppTheme

class MainPageFragment : Fragment() {
    private val repository = TaskRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    MainPage(
                        onTasksClicked = {
                            findNavController()
                                .navigate(MainPageFragmentDirections.actionMainPageFragmentToListOfTasksFragment())
                        },
                        onGameplansClicked = {
                            findNavController()
                                .navigate(MainPageFragmentDirections.actionMainPageFragmentToListOfGameplansFragment())
                        },
                        onPlayRandomClicked = {
                            repository.fetchTasks { tasks ->
                                val game = createShuffledGame(tasks)
                                navigateToAddPlayers(game)
                            }
                        },
                    )
                }

            }
        }

    private fun createShuffledGame(tasks: List<Task>): Game {
        val shuffledTasks = tasks.shuffled()
        return Game(
            id = generateUniqueId(),
            currentTask = 0,
            gameplan = Gameplan(
                id = generateGameplanId(),
                name = "Random Shuffle Session",
                listOfTaskIds = shuffledTasks.map { it.id }
            ),
            listOfPlayers = emptyList()
        )
    }

    private fun navigateToAddPlayers(game: Game) {
        findNavController().navigate(
            MainPageFragmentDirections.actionMainPageFragmentToAddPlayersPageFragment(game)
        )
    }

    private fun generateUniqueId() = System.currentTimeMillis()  // Replace with proper ID generation
    private fun generateGameplanId() = "gameplan_${System.currentTimeMillis()}"
}

