package com.muni.taskmajster.ui.add_players_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.repository.TaskMajsterRepository
import com.muni.taskmajster.ui.playing_task_page.PlayingTaskPageFragmentArgs

class AddPlayersPageFragment: Fragment() {

    private val args: PlayingTaskPageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                AddPlayersPage(
                    game = args.game,
                    onArrowBackClicked = {
                        findNavController().navigateUp()
                    },
                    onPlayClicked = {
                        findNavController()
                            .navigate(AddPlayersPageFragmentDirections.actionAddPlayersPageFragmentToPlayingTaskPageFragment(
                                args.game
                            ))
                    }
                )
            }
        }
}