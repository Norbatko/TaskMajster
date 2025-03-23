package com.muni.taskmajster.ui.list_of_gameplans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.muni.taskmajster.ui.list_of_tasks.ListOfTasksFragmentDirections

class ListOfGameplansFragment: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                ListOfGameplans(
                    onArrowBackClicked = {
                        findNavController()
                            .navigateUp()
                    },
                    onGameplanClick = {
                        findNavController()
                            .navigate(ListOfGameplansFragmentDirections.actionListOfGameplansFragmentToGameplanDetailFragment())
                    },
                )
            }
        }
}
