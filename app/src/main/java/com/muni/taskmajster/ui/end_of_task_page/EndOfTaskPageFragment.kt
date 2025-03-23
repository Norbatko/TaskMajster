package com.muni.taskmajster.ui.end_of_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

const val taskName = "Task 1"

class EndOfTaskPageFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                EndOfTaskPage(
                    onArrowBackClicked = {
                        findNavController().navigateUp()
                    },
                    taskName = taskName
                )
            }
        }
}