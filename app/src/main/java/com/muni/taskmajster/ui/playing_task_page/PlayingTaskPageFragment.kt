package com.muni.taskmajster.ui.playing_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class PlayingTaskPageFragment: Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            ComposeView(requireContext()).apply {
                setContent {
                    PlayingTaskPage()
                }
            }
}