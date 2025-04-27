package com.muni.taskmajster.ui.list_of_tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.data.Task
import com.muni.taskmajster.repository.TaskRepository

class ListOfTasksFragment : Fragment() {
    private val repository = TaskRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            // --- STATE ---
            var tasks by remember { mutableStateOf<List<Task>?>(null) }

            // --- ASYNC FETCH ---
            LaunchedEffect(Unit) {
                repository.fetchTasks { fetchedTasks ->
                    tasks = fetchedTasks
                }
            }

            // --- UI ---
            when {
                tasks == null -> {
                    // Loading indicator
                    Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                tasks!!.isEmpty() -> {
                    // Empty state
                    Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        Text("No tasks found.")
                    }
                }
                else -> {
                    // Show list
                    ListOfTasks(
                        listOfTasks = tasks!!,
                        onArrowBackClicked = { findNavController().navigateUp() },
                        onTaskClicked = { task ->
                            findNavController().navigate(
                                ListOfTasksFragmentDirections.actionListOfTasksFragmentToTaskDetailFragment(
                                    task = task
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}
