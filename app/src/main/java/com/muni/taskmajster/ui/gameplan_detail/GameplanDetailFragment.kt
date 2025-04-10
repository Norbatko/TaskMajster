package com.muni.taskmajster.ui.gameplan_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class GameplanDetailFragment: Fragment() {

    private val args: GameplanDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                GameplanDetail(
                    gameplan = args.gameplan,
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
