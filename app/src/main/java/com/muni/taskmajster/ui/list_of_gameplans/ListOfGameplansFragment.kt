package com.muni.taskmajster.ui.list_of_gameplans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.muni.taskmajster.repository.TaskMajsterRepository

class ListOfGameplansFragment: Fragment()  {
    private val repository = TaskMajsterRepository() // TODO read from real repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                val gameplans = repository.getFakeGameplans()

                ListOfGameplans(
                    listOfGameplans = gameplans,
                    onArrowBackClicked = {
                        findNavController()
                            .navigateUp()
                    },
                    onGameplanClicked = { gameplan ->
                        findNavController()
                            .navigate(
                                ListOfGameplansFragmentDirections.actionListOfGameplansFragmentToGameplanDetailFragment(
                                    gameplan = gameplan
                                )
                            )
                    },
                )
            }
        }
}
