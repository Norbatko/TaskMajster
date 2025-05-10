package com.muni.taskmajster.view.ui.task.task_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.viewModel.TaskViewModel

class TaskDetailFragment: Fragment() {

    private val args: TaskDetailFragmentArgs by navArgs()
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
            taskViewModel.setTask(args.task)

            setContent {
                val task = taskViewModel.task.observeAsState(args.task).value

                TaskDetail(
                    task = task,
                    onArrowBackClicked = {
                        findNavController().navigateUp()
                    },
                    onAddToGameplanClicked = {
                        findNavController().navigate(
                            TaskDetailFragmentDirections
                                .actionTaskDetailFragmentToListOfGameplansFragment(task)
                        )
                    },
                    onPlayClicked = { game ->
                        findNavController().navigate(
                            TaskDetailFragmentDirections.actionTaskDetailFragmentToAddPlayersPageFragment(game)
                        )
                    },
                    onEditClicked = {
                        findNavController().navigate(
                            TaskDetailFragmentDirections.actionTaskDetailFragmentToTaskFormFragment(task)
                        )
                    },
                    onDeleteClicked = {
                        taskViewModel.deleteTask(task.id) {
                            findNavController().navigateUp()
                        }
                    }
                )
            }
        }
}
