package com.muni.taskmajster.view.ui.playing_task_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.viewModel.PlayingTaskViewModel
import kotlin.getValue

class PlayingTaskPageFragment: Fragment() {

    private val args: PlayingTaskPageFragmentArgs by navArgs()
    private val viewModel: PlayingTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTasksByIds(args.game.gameplan.listOfTaskIds)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                val tasks by viewModel.tasks.observeAsState(emptyList())
                val loadingTasks by viewModel.loadingTasks.observeAsState(false)

                if (loadingTasks) {
                    Log.d("LOADING", "Playing task page loading tasks")
                }

                when {
                    loadingTasks -> {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    else -> {

                        PlayingTaskPage(
                            game = args.game,
                            listOfGameplanTasks = tasks,
                            onDoneClicked = { updatedGame ->
                                findNavController()
                                    .navigate(
                                        PlayingTaskPageFragmentDirections
                                            .actionPlayingTaskPageFragmentToEndOfTaskPageFragment(
                                                updatedGame
                                            )
                                    )
                            },
                            onArrowBackClicked = {
                                findNavController()
                                    .navigateUp()
                            },
                        )
                    }
                }
            }
        }
}