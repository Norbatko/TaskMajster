package com.muni.taskmajster.ui.gameplan_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.muni.taskmajster.repository.TaskMajsterRepository

class GameplanDetailFragment: Fragment() {
    private val repository = TaskMajsterRepository() // TODO probabbly with args?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                val gameplans = repository.getFakeGameplans()

                GameplanDetail(
                    gameplan = gameplans.first(),
                    onArrowBackClick = {
                        findNavController()
                            .navigateUp()
                    }
                )
            }
        }
}