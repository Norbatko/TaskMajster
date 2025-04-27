package com.muni.taskmajster.ui.list_of_gameplans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.muni.taskmajster.data.Gameplan
import com.muni.taskmajster.repository.GameplanRepository
import android.util.Log

class ListOfGameplansFragment : Fragment() {
    private val repository = GameplanRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            // 1. Hold the gameplans in a Compose state
            val (gameplans, setGameplans) = remember { mutableStateOf<List<Gameplan>>(emptyList()) }

            // 2. Fetch gameplans once when the Composable is shown
            LaunchedEffect(Unit) {
                repository.fetchGameplans { fetchedGameplans ->
                    setGameplans(fetchedGameplans)
                }
            }

            // 3. Pass the loaded gameplans to your UI
            ListOfGameplans(
                listOfGameplans = gameplans,
                onArrowBackClicked = { findNavController().navigateUp() },
                onGameplanClicked = { gameplan ->
                    findNavController().navigate(
                        ListOfGameplansFragmentDirections
                            .actionListOfGameplansFragmentToGameplanDetailFragment(gameplan)
                    )
                },
            )
        }
    }
}

