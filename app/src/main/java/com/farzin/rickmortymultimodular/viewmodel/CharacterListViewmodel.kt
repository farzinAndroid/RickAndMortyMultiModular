package com.farzin.rickmortymultimodular.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.network.domain.model.LocalCharacterPage
import com.farzin.rickmortymultimodular.repository.CharacterRepo
import com.farzin.rickmortymultimodular.ui.screens.character_list.CharacterListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewmodel @Inject constructor(
    private val characterRepo: CharacterRepo,
) : ViewModel() {


    private val _viewState =
        MutableStateFlow<CharacterListScreenViewState>(CharacterListScreenViewState.Loading)
    val viewState: StateFlow<CharacterListScreenViewState> = _viewState.asStateFlow()

    private val fetchCharacterPages = mutableListOf<LocalCharacterPage>()


    fun getInitialPage() = viewModelScope.launch(Dispatchers.IO) {
        if (fetchCharacterPages.isNotEmpty()) return@launch
        characterRepo.getCharacterList(pageNumber = 1)
            .onSuccess { characterPage ->
                fetchCharacterPages.clear()
                fetchCharacterPages.add(characterPage)
                _viewState.update {
                    return@update CharacterListScreenViewState.GridDisplay(characterPage.characters)
                }
            }
            .onFailure {
                // todo failure
            }
    }


    fun getNextPage() = viewModelScope.launch(Dispatchers.IO) {
        val nextCharacterPage = fetchCharacterPages.size + 1
        characterRepo.getCharacterList(nextCharacterPage)
            .onSuccess {characterPage->
                fetchCharacterPages.clear()
                fetchCharacterPages.add(characterPage)
                _viewState.update {currentState->
                    val currentCharacters = (currentState as? CharacterListScreenViewState.GridDisplay)?.characters ?: emptyList()
                    return@update CharacterListScreenViewState.GridDisplay(currentCharacters + characterPage.characters)
                }
            }
            .onFailure {

            }
    }

}