package com.muni.taskmajster.ui.gameplan_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class GameplanDetailFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                GameplanDetail(
                    onArrowBackClick = {
                        findNavController()
                            .navigateUp()
                    }
                )
            }
        }
}