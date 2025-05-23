package com.muni.taskmajster.view.ui.gameplan.gameplan_detail

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.view.ui.theme.AppTheme
import com.muni.taskmajster.viewModel.GameplanViewModel
import kotlin.getValue

class GameplanDetailFragment : Fragment() {

    private val args: GameplanDetailFragmentArgs by navArgs()
    private lateinit var gameplanViewModel: GameplanViewModel

    override fun onResume() {
        super.onResume()
        gameplanViewModel.refreshGameplan(args.gameplan.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        gameplanViewModel = ViewModelProvider(requireActivity())[GameplanViewModel::class.java]
        gameplanViewModel.setGameplan(args.gameplan)

        setContent {
            AppTheme {
                val gameplan = gameplanViewModel.gameplan.observeAsState(args.gameplan).value
                val tasks by gameplanViewModel.tasks.observeAsState(emptyList())
                val loading by gameplanViewModel.loading.observeAsState(false)

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
                        GameplanDetail(
                            gameplan = gameplan,
                            listOfGameplanTasks = tasks,
                            onArrowBackClicked = {
                                findNavController().navigateUp()
                            },
                            onTaskClicked = { task ->
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections
                                        .actionGameplanDetailFragmentToTaskDetailFragment(task)
                                )
                            },
                            onPlayClicked = { game ->
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections
                                        .actionGameplanDetailFragmentToAddPlayersPageFragment(game)
                                )
                            },
                            onEditClicked = {
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections
                                        .actionGameplanDetailFragmentToGameplanFormFragment(gameplan)
                                )
                            },
                            onDeleteClicked = {
                                gameplanViewModel.deleteGameplan(gameplan.id) {
                                    findNavController().navigateUp()
                                }
                            },
                            onAddNewTaskClicked = {
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections
                                        .actionGameplanDetailFragmentToListOfTasksFragment(gameplan)
                                )
                            },
                            onRemoveFromGameplanClicked = { taskToRemove ->
                                val updatedTaskIds = gameplan.listOfTaskIds.toMutableList().apply {
                                    remove(taskToRemove.id)
                                }
                                val updatedGameplan = gameplan.copy(listOfTaskIds = updatedTaskIds)
                                gameplanViewModel.updateGameplan(updatedGameplan)
                            }
                        )
                    }
                }
            }
        }
    }
}

