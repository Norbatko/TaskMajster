package com.muni.taskmajster.ui.list_of_tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.repository.TaskMajsterRepository

class ListOfTasksFragment : Fragment() {
    private val repository = TaskMajsterRepository() // TODO read from real repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val tasks = repository.getFakeTasks()

            ListOfTasks(
                listOfTasks = tasks,
                onArrowBackClicked = { findNavController().navigateUp() },
                onTaskClicked = { task ->
                    findNavController().navigate(
                        ListOfTasksFragmentDirections.actionListOfTasksFragmentToTaskDetailFragment(
                            task = task
                        )
                    )
                },
            )
        }
    }
}
