package com.muni.taskmajster.ui.list_of_gameplans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class ListOfGameplansFragment: Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                ListOfGameplans(
                    onArrowBackClicked = {
                        findNavController()
                            .navigateUp()
                    }
                )
            }
        }
}
