package com.muni.taskmajster.ui.task_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class TaskDetailFragment: Fragment() {

    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                TaskDetail(
                    task = args.task,
                    onArrowBackClicked = {
                        findNavController().navigateUp()
                    },
                    onPlayClicked = { game ->
                        findNavController().navigate(
                            TaskDetailFragmentDirections.actionTaskDetailFragmentToAddPlayersPageFragment(
                                game
                            )
                        )
                    },
                )
            }
        }
}