package com.farzin.rickmortymultimodular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.rickmortymultimodular.repository.CharacterRepo
import com.farzin.rickmortymultimodular.ui.screens.character_detail.CharacterDetailsViewState
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.DataPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewmodel @Inject constructor(
    private val characterRepo: CharacterRepo,
) : ViewModel() {

    private val _characterDetailsViewState =
        MutableStateFlow<CharacterDetailsViewState>(CharacterDetailsViewState.Loading)
    val characterDetailsViewState = _characterDetailsViewState.asStateFlow()


    fun getCharacterDetails(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _characterDetailsViewState.update { CharacterDetailsViewState.Loading }
        characterRepo.getCharacterDetails(characterId)
            .onSuccess { character ->
                val dataPoints = buildList {
                    add(DataPoint("Last known location", character.location.name))
                    add(DataPoint("Gender", character.gender.gender))
                    add(DataPoint("Species", character.species))
                    add(DataPoint("Origin", character.origin.name))
                    add(DataPoint("Episode count", character.episode.size.toString()))
                    character.type.takeIf { it.isNotEmpty() }?.let { type ->
                        add(DataPoint("Type", type))
                    }
                }

                _characterDetailsViewState.update {
                    return@update CharacterDetailsViewState.Success(
                        character = character,
                        dataPoints = dataPoints
                    )
                }
            }
            .onFailure { exception ->
                _characterDetailsViewState.update {
                    return@update CharacterDetailsViewState.Error(
                        message = exception.message.toString()
                    )
                }
            }
    }


}