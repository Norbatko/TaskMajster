package com.muni.taskmajster.view.ui.task.task_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.muni.taskmajster.model.repository.TaskRepository
import com.muni.taskmajster.viewModel.TaskViewModel

class TaskFormFragment : Fragment() {

    private val args: TaskFormFragmentArgs by navArgs()
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        setContent {
            val isEdit = args.task != null
            TaskForm(
                initialTask = args.task,
                onSaveClicked = { task ->
                    if (isEdit) {
                        taskViewModel.updateTask(task)
                        findNavController().navigateUp()
                    } else {
                        val repository = TaskRepository()
                        repository.addTask(task) {
                            taskViewModel.setTask(task)
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


