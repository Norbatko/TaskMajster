package com.muni.taskmajster.view.ui.game.add_players_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.view.ui.theme.AppTheme

class AddPlayersPageFragment: Fragment() {

    private val args: AddPlayersPageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    AddPlayersPage(
                        game = args.game,
                        onArrowBackClicked = {
                            findNavController().navigateUp()
                        },
                        onPlayClicked = { updatedGame ->
                            findNavController().navigate(
                                AddPlayersPageFragmentDirections
                                    .actionAddPlayersPageFragmentToPlayingTaskPageFragment(updatedGame)
                            )
                        }
                    )
                }

            }
        }
}