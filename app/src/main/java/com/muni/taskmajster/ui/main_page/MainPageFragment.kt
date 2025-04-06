package com.muni.taskmajster.ui.main_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainPageFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            setContent {
                MainPage(
                    onTasksClicked = {
                        findNavController()
                            .navigate(MainPageFragmentDirections.actionMainPageFragmentToListOfTasksFragment())
                        print("detekuji menu klik")
                    },
                    onGameplansClicked = {
                        findNavController()
                            .navigate(MainPageFragmentDirections.actionMainPageFragmentToListOfGameplansFragment())
                    },
                    onPlayRandomClicked = {
                        findNavController()
                            .navigate(MainPageFragmentDirections.actionMainPageFragmentToPlayingTaskPageFragment())
                    },
                    onPlayGameplanClicked = {
                        findNavController()
                            .navigate(MainPageFragmentDirections.actionMainPageFragmentToAddPlayersPageFragment())
                    }
                )
            }
        }
}
