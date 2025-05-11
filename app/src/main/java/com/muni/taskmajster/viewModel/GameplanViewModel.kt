package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.repository.GameplanRepository
import com.muni.taskmajster.model.data.Task
import com.muni.taskmajster.model.repository.TaskRepository

class GameplanViewModel(
    private val taskRepository: TaskRepository = TaskRepository(),
    private val gameplanRepository: GameplanRepository = GameplanRepository()
) : ViewModel() {

    private val _gameplan = MutableLiveData<Gameplan>()
    val gameplan: LiveData<Gameplan> get() = _gameplan

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun setGameplan(gameplan: Gameplan) {
        _gameplan.value = gameplan
        loadTasksByIds(gameplan.listOfTaskIds)
    }

    fun updateGameplan(gameplan: Gameplan) {
        gameplanRepository.updateGameplan(gameplan) { success ->
            if (success) {
                _gameplan.postValue(gameplan)
                loadTasksByIds(gameplan.listOfTaskIds)
            }
        }
    }

    fun refreshGameplan(gameplanId: String) {
        gameplanRepository.fetchGameplanById(gameplanId) { updatedGameplan ->
            updatedGameplan?.let {
                _gameplan.postValue(it)
                loadTasksByIds(it.listOfTaskIds)
            }
        }
    }

    fun loadTasksByIds(ids: List<String>) {
        _loading.value = true
        taskRepository.fetchTasksByIds(ids) { fetchedTasks ->
            _tasks.postValue(fetchedTasks)
            _loading.postValue(false)
        }
    }

    fun deleteGameplan(gameplanId: String, onResult: (Boolean) -> Unit) {
        gameplanRepository.deleteGameplan(gameplanId, onResult)
    }
}
