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
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.viewModel.GameplanDetailViewModel
import kotlin.getValue

class GameplanDetailFragment: Fragment() {

    private val args: GameplanDetailFragmentArgs by navArgs()
    private val viewModel: GameplanDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTasksByIds(args.gameplan.listOfTaskIds)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
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
                        GameplanDetail(
                            gameplan = args.gameplan,
                            listOfGameplanTasks = tasks,
                            onArrowBackClicked = {
                                findNavController().navigateUp()
                            },
                            onTaskClicked = { task ->
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections.actionGameplanDetailFragmentToTaskDetailFragment(
                                        task
                                    )
                                )
                            },
                            onPlayClicked = { game ->
                                findNavController().navigate(
                                    GameplanDetailFragmentDirections.actionGameplanDetailFragmentToAddPlayersPageFragment(
                                        game
                                    )
                                )
                            },
                        )
                    }
                }
            }
        }
}
