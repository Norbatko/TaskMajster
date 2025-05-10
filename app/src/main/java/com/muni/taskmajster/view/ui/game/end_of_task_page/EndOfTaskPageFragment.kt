package com.muni.taskmajster.view.ui.game.end_of_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.muni.taskmajster.viewModel.EndOfTaskPageViewModel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.muni.taskmajster.view.ui.theme.AppTheme

class EndOfTaskPageFragment: Fragment() {

    private val args: EndOfTaskPageFragmentArgs by navArgs()
    private val viewModel: EndOfTaskPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTasksByIds(args.game.gameplan.listOfTaskIds)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            val nextTask = args.game.currentTask + 1
            val isLastTask = nextTask >= args.game.gameplan.listOfTaskIds.size

            setContent {
                AppTheme {
                    val tasks by viewModel.tasks.observeAsState(emptyList())
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

                            EndOfTaskPage(
                                game = args.game,
                                viewModel = viewModel,
                                listOfGameplanTasks = tasks,
                                lastTask = isLastTask,
                                onArrowBackClicked = {
                                    findNavController()
                                        .navigate(EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToMainPage())
                                },
                                onNextTaskClicked = if (isLastTask) ({}) else { game ->
                                    val updatedGame = game.copy(currentTask = nextTask)
                                    findNavController().navigate(
                                        EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToPlayingTaskPageFragment(
                                            updatedGame
                                        )
                                    )
                                },
                                onFinalizeClicked = { // TODO now back to menu, maybe some 3 winner screen? or completely remove and leave just arrow back?
                                    findNavController()
                                        .navigate(EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToMainPage())
                                }
                            )
                        }
                    }
                }

            }
        }
}