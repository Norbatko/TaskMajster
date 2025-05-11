package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.TaskRepository

class PlayingTaskViewModel (
    private val taskRepository: TaskRepository = TaskRepository(),
) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _gameplan = MutableLiveData<Gameplan?>()
    val gameplan: LiveData<Gameplan?> = _gameplan

    private val _loadingTasks = MutableLiveData<Boolean>()
    val loadingTasks: LiveData<Boolean> = _loadingTasks

    fun loadTasksByIds(ids: List<String>) {
        _loadingTasks.value = true
        taskRepository.fetchTasksByIds(ids) { fetchedTasks ->
            _tasks.postValue(fetchedTasks)
            _loadingTasks.postValue(false)
        }
    }
}