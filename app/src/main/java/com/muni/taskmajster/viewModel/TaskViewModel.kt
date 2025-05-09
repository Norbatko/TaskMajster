package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.TaskRepository

class TaskViewModel : ViewModel() {
    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task> get() = _task

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    fun setTask(task: Task) {
        _task.value = task
    }

    fun updateTask(task: Task) {
        val repository = TaskRepository()
        repository.updateTask(task) { success ->
            if (success) {
                _task.value = task
            }
            // TODO error popup?
        }
    }

    fun deleteTask(taskId: String, onResult: (Boolean) -> Unit) {
        val repository = TaskRepository()
        repository.deleteTask(taskId) { success ->
            onResult(success)
        }
    }
}