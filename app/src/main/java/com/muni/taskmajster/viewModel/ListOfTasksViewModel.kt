package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.TaskRepository

class ListOfTasksViewModel (
    private val taskRepository: TaskRepository = TaskRepository()
) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun loadTasks() {
        _loading.value = true
        taskRepository.fetchTasks { fetchedTasks ->
            _tasks.postValue(fetchedTasks)
            _loading.postValue(false)
        }
    }
}