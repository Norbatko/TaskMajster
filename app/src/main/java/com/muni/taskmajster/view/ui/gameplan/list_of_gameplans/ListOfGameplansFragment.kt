package com.muni.taskmajster.view.ui.gameplan.list_of_gameplans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.muni.taskmajster.viewModel.ListOfGameplansViewModel
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.model.data.Task
import kotlin.getValue

class ListOfGameplansFragment : Fragment() {
    private val viewModel: ListOfGameplansViewModel by viewModels()
    private val args: ListOfGameplansFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadGameplans()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val gameplans by viewModel.gameplans.observeAsState(emptyList())
            val loading by viewModel.loading.observeAsState(false)

            when {
                loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    ListOfGameplans(
                        listOfGameplans = gameplans,
                        onArrowBackClicked = { findNavController().navigateUp() },
                        onGameplanClicked = { gameplan ->
                            findNavController().navigate(
                                ListOfGameplansFragmentDirections
                                    .actionListOfGameplansFragmentToGameplanDetailFragment(gameplan)
                            )
                        },
                        onAddGameplanClicked = {
                            findNavController().navigate(
                                ListOfGameplansFragmentDirections
                                    .actionListOfGameplansFragmentToGameplansFormFragment(null)
                            )
                        },

                        addTaskToGameplan = args.task != null,
                        task = args.task,
                        onAddTaskToGameplanClicked = { gameplan ->
                            val task: Task? = args.task
                            if (task != null) {
                                val updatedGameplan = gameplan.copy(
                                    listOfTaskIds = gameplan.listOfTaskIds + task.id
                                )
                                viewModel.updateGameplan(updatedGameplan) { success ->
                                    if (success) {
                                        findNavController().navigateUp()
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

