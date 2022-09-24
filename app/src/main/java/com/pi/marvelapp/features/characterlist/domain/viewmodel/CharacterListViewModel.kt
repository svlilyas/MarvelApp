package com.pi.marvelapp.features.characterlist.domain.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewModel
import com.pi.marvelapp.core.utils.AppConstants.Companion.THIRTY_INT
import com.pi.marvelapp.features.characterlist.domain.usecase.CharacterListUseCase
import com.pi.marvelapp.features.characterlist.domain.viewaction.CharacterListAction
import com.pi.marvelapp.features.characterlist.domain.viewstate.CharacterListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase
) : BaseViewModel<CharacterListViewState, CharacterListAction>(CharacterListViewState(null)) {

    //private val navDirections = CharacterListFragmentDirections

    fun navigateToCharacterDetails(characterInfo: CharacterInfo) {
        /*val direction = navDirections.actionNoteListFragmentToCreateEditNoteFragment(
            character = character,
            isNoteExist = true
        )
        navigate(direction)*/
    }

    fun fetchAllCharacters(limit: Int?) {
        viewModelScope.launch {
            val response =
                useCase.fetchAllCharacters(limit = limit ?: THIRTY_INT).cachedIn(viewModelScope)
            response.collectLatest {
                sendAction(CharacterListAction.GetCharacterListSuccess(it))
            }
        }
    }

    override fun onReduceState(viewAction: CharacterListAction): CharacterListViewState =
        when (viewAction) {
            CharacterListAction.GetCharacterListLoading -> state.copy(
                uiState = UiState.LOADING
            )
            is CharacterListAction.GetCharacterListSuccess -> state.copy(
                characterList = viewAction.characterList,
                uiState = UiState.SUCCESS
            )
            CharacterListAction.GetCharacterListFailure -> state.copy(
                uiState = UiState.ERROR
            )
        }
}
