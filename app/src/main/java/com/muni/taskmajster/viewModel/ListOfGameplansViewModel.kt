package com.muni.taskmajster.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muni.taskmajster.model.data.Gameplan
import com.muni.taskmajster.model.repository.GameplanRepository

class ListOfGameplansViewModel (
    private val gameplanRepository: GameplanRepository = GameplanRepository()
) : ViewModel() {
    private val _gameplans = MutableLiveData<List<Gameplan>>()
    val gameplans: LiveData<List<Gameplan>> = _gameplans

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun loadGameplans() {
        _loading.value = true
        gameplanRepository.fetchGameplans { fetchedGameplans ->
            _gameplans.postValue(fetchedGameplans)
            _loading.postValue(false)
        }
    }

    fun updateGameplan(updatedGameplan: Gameplan, onResult: (Boolean) -> Unit) {
        gameplanRepository.updateGameplan(updatedGameplan) { success ->
            if (success) { // refresh
                loadGameplans()
            }
            onResult(success)
        }
    }

}