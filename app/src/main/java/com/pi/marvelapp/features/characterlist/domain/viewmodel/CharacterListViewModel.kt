package com.pi.marvelapp.features.characterlist.domain.viewmodel

import androidx.lifecycle.viewModelScope
import com.pi.data.remote.response.Character
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewModel
import com.pi.marvelapp.features.characterlist.domain.usecase.CharacterListUseCase
import com.pi.marvelapp.features.characterlist.domain.viewaction.CharacterListAction
import com.pi.marvelapp.features.characterlist.domain.viewstate.CharacterListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase
) : BaseViewModel<CharacterListViewState, CharacterListAction>(CharacterListViewState()) {

    //private val navDirections = CharacterListFragmentDirections

    fun navigateToCharacterDetails(character: Character) {
        /*val direction = navDirections.actionNoteListFragmentToCreateEditNoteFragment(
            character = character,
            isNoteExist = true
        )
        navigate(direction)*/
    }

    fun fetchAllCharacters() {
        viewModelScope.launch {
            useCase.fetchAllCharacters().collect { list ->
                sendAction(CharacterListAction.GetCharacterListSuccess(list))
            }
        }
    }

    override fun onReduceState(viewAction: CharacterListAction): CharacterListViewState =
        when (viewAction) {
            is CharacterListAction.GetCharacterListSuccess -> state.copy(
                characterList = viewAction.characterList,
                uiState = UiState.SUCCESS
            )
            CharacterListAction.GetCharacterListFailure -> state.copy(
                characterList = emptyList(),
                uiState = UiState.ERROR
            )
        }
}
