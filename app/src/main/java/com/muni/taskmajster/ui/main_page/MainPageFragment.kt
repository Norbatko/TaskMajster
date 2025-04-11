package com.muni.taskmajster.ui.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.data.Game
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.repository.TaskMajsterRepository

class MainPageFragment: Fragment() {
    private val repository = TaskMajsterRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
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
                        findNavController()
                            .navigate(MainPageFragmentDirections.actionMainPageFragmentToAddPlayersPageFragment(
                                // TODO on click create new game in the real repository with all existing tasks shuffled
                                Game(
                                    id = 1,
                                    currentTask = 0,
                                    gameplan = Gameplan(
                                        id = 1,
                                        name = "All tasks shuffled",
                                        listOfTasks = repository.getFakeTasks().shuffled(),
                                    ),
                                    listOfPlayers = emptyList())
                            ))
                    },
                )
            }
        }
}
