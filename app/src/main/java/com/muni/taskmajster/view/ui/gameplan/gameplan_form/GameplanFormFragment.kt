package com.muni.taskmajster.view.ui.gameplan.gameplan_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.model.repository.GameplanRepository
import com.muni.taskmajster.view.ui.theme.AppTheme
import com.muni.taskmajster.viewModel.GameplanViewModel
import kotlin.getValue

class GameplanFormFragment : Fragment() {

    private val args: GameplanFormFragmentArgs by navArgs()
    private lateinit var gameplanViewModel: GameplanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        gameplanViewModel = ViewModelProvider(requireActivity())[GameplanViewModel::class]

        setContent {
            AppTheme {
                val isEdit = args.gameplan != null
                GameplanForm(
                    initialGameplan = args.gameplan,
                    onSaveClicked = { gameplan ->
                        if (isEdit) {
                            gameplanViewModel.updateGameplan(gameplan)
                            findNavController().navigateUp()
                        } else {
                            val repository = GameplanRepository()
                            repository.addGameplan(gameplan) {
                                gameplanViewModel.setGameplan(gameplan)
                                findNavController().navigateUp()
                            }
                        }
                    },
                    onCancelClicked = {
                        findNavController().navigateUp()
                    },
                    isEditMode = isEdit
                )
            }

        }
    }
}


