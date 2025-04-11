package com.muni.taskmajster.ui.end_of_task_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

class EndOfTaskPageFragment: Fragment() {

    private val args: EndOfTaskPageFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(requireContext()).apply {
            val nextTask = args.game.currentTask + 1
            val isLastTask = nextTask >= args.game.gameplan.listOfTasks.size

            setContent {
                EndOfTaskPage(
                    game = args.game,
                    lastTask = isLastTask,
                    onArrowBackClicked = {
                        findNavController()
                            .navigate(EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToMainPage())
                    },
                    onNextTaskClicked = if (isLastTask) ({}) else { game ->
                        val updatedGame = game.copy(currentTask = nextTask)
                        findNavController().navigate(
                            EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToPlayingTaskPageFragment(updatedGame)
                        )
                    },
                    onFinalizeClicked = { // TODO now back to menu, maybe some 3 winner screen? or completely remove and leave just arrow back?
                        findNavController()
                            .navigate(EndOfTaskPageFragmentDirections.actionEndOfTaskPageFragmentToMainPage())
                    }
                )
            }
        }
}