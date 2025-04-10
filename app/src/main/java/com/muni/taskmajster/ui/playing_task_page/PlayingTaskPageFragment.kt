package com.muni.taskmajster.ui.playing_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.repository.TaskMajsterRepository

class PlayingTaskPageFragment: Fragment() {
    private val repository = TaskMajsterRepository() // TODO probabbly with args?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                val game = repository.getFakeGame()

                PlayingTaskPage(
                    game = game,
                    onDoneClicked = {
                        findNavController()
                            .navigate(PlayingTaskPageFragmentDirections
                                .actionPlayingTaskPageFragmentToEndOfTaskPageFragment())
                    },
                    onArrowBackClicked = {
                        findNavController()
                            .navigateUp()
                    },
                )
            }
        }
}