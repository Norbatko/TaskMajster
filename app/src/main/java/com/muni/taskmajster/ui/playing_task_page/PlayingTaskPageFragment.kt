package com.muni.taskmajster.ui.playing_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class PlayingTaskPageFragment: Fragment() {

    private val args: PlayingTaskPageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                PlayingTaskPage(
                    game = args.game,
                    onDoneClicked = {
                        findNavController()
                            .navigate(PlayingTaskPageFragmentDirections
                                .actionPlayingTaskPageFragmentToEndOfTaskPageFragment(args.game))
                    },
                    onArrowBackClicked = {
                        findNavController()
                            .navigateUp()
                    },
                )
            }
        }
}