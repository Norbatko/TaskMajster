package com.muni.taskmajster.ui.playing_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


const val taskDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
const val taskName = "Task 1"


class PlayingTaskPageFragment: Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            ComposeView(requireContext()).apply {
                setContent {
                    PlayingTaskPage(
                        taskName = taskName,
                        taskDescription = taskDescription,
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