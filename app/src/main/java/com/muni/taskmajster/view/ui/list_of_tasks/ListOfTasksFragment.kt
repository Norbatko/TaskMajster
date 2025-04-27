package com.muni.taskmajster.view.ui.list_of_tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.viewModel.ListOfTasksViewModel
import kotlin.getValue

class ListOfTasksFragment : Fragment() {
    private val viewModel: ListOfTasksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTasks()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val tasks by viewModel.tasks.observeAsState(emptyList())
            val loading by viewModel.loading.observeAsState(false)

            if (loading) {
                Log.d("LOADING", "List of gameplans page loading gameplans")
            }

            when {
                loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    // Show list
                    ListOfTasks(
                        listOfTasks = tasks,
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
