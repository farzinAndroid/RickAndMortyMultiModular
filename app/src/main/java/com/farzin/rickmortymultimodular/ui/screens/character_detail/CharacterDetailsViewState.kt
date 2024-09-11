package com.farzin.rickmortymultimodular.ui.screens.character_detail

import com.farzin.network.domain.model.LocalCharacter
import com.farzin.rickmortymultimodular.ui.screens.character_detail.components.DataPoint

sealed class CharacterDetailsViewState {

    data object Loading : CharacterDetailsViewState()

    data class Success(
        val character: LocalCharacter,
        val dataPoints: List<DataPoint>,
    ) : CharacterDetailsViewState()

    data class Error(val message: String) : CharacterDetailsViewState()


}