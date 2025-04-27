package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.GameplanRepository
import com.muni.taskmajster.model.repository.TaskRepository

class PlayingTaskViewModel (
    private val taskRepository: TaskRepository = TaskRepository(),
    private val gameplanRepository: GameplanRepository = GameplanRepository()
) : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _gameplan = MutableLiveData<Gameplan?>()
    val gameplan: LiveData<Gameplan?> = _gameplan

    private val _loadingTasks = MutableLiveData<Boolean>()
    val loadingTasks: LiveData<Boolean> = _loadingTasks

    private val _loadingGameplans = MutableLiveData<Boolean>()
    val loadingGameplans: LiveData<Boolean> = _loadingGameplans

    fun loadTasksByIds(ids: List<String>) {
        _loadingTasks.value = true
        taskRepository.fetchTasksByIds(ids) { fetchedTasks ->
            _tasks.postValue(fetchedTasks)
            _loadingTasks.postValue(false)
        }
    }

    fun loadGameplanById(id: String) {
        _loadingGameplans.value = true
        gameplanRepository.fetchGameplanById (id) { fetchedGameplan ->
            _gameplan.postValue(fetchedGameplan)
            _loadingGameplans.postValue(false)
        }
    }
}