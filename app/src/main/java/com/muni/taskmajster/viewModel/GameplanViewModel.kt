package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.repository.GameplanRepository

class GameplanViewModel : ViewModel() {
    private val _gameplan = MutableLiveData<Gameplan>()
    val gameplan: LiveData<Gameplan> get() = _gameplan

    private val _gameplans = MutableLiveData<List<Gameplan>>()
    val gameplans: LiveData<List<Gameplan>> get() = _gameplans

    fun setGameplan(gameplan: Gameplan) {
        _gameplan.value = gameplan
    }

    fun updateGameplan(gameplan: Gameplan) {
        val repository = GameplanRepository()
        repository.updateGameplan(gameplan) { success ->
            if (success) {
                _gameplan.value = gameplan
            }
            // TODO error popup?
        }
    }

    fun deleteGameplan(gameplanId: String, onResult: (Boolean) -> Unit) {
        val repository = GameplanRepository()
        repository.deleteGameplan(gameplanId) { success ->
            onResult(success)
        }
    }
}