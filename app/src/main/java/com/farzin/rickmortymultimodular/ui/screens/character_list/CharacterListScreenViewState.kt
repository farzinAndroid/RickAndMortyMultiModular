package com.farzin.rickmortymultimodular.ui.screens.character_list

import com.farzin.network.domain.model.LocalCharacter

sealed interface CharacterListScreenViewState {
    data object Loading : CharacterListScreenViewState
    data class GridDisplay(
        val characters: List<LocalCharacter> = emptyList()
    ) : CharacterListScreenViewState
}