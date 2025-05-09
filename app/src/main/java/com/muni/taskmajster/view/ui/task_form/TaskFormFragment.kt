package com.muni.taskmajster.view.ui.task_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.model.repository.TaskRepository

class
TaskFormFragment : Fragment() {

    private val args: TaskFormFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val isEdit = args.task != null
            TaskForm(
                initialTask = args.task,
                onSave = { task ->
                    val repository = TaskRepository()
                    if (isEdit) { // update
                        repository.updateTask(task) {
                            findNavController().navigateUp()
                        }
                    } else { // create
                        repository.addTask(task) {
                            findNavController().navigateUp()
                        }
                    }
                },
                onCancel = {
                    findNavController().navigateUp()
                },
                isEditMode = isEdit
            )
        }
    }
}

