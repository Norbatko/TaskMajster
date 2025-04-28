package com.muni.taskmajster.view.ui.list_of_gameplans

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
import androidx.navigation.findNavController
import com.muni.taskmajster.viewModel.ListOfGameplansViewModel
import kotlin.getValue

class ListOfGameplansFragment : Fragment() {
    private val viewModel: ListOfGameplansViewModel by viewModels()

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
                        contentAlignment = androidx.compose.ui.Alignment.Center
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
                    )
                }
            }
        }
    }
}

