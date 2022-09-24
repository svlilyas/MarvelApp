package com.pi.marvelapp.features.characterlist.domain.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewModel
import com.pi.marvelapp.core.utils.AppConstants.Companion.PREFIX_HTTP
import com.pi.marvelapp.core.utils.AppConstants.Companion.PREFIX_HTTPS
import com.pi.marvelapp.core.utils.AppConstants.Companion.PREFIX_IMG_XLARGE
import com.pi.marvelapp.core.utils.AppConstants.Companion.STRING_EMPTY
import com.pi.marvelapp.core.utils.AppConstants.Companion.THIRTY_INT
import com.pi.marvelapp.core.utils.AppConstants.Companion.ZERO_INT
import com.pi.marvelapp.core.utils.createThumbnailUrl
import com.pi.marvelapp.features.characterlist.domain.usecase.CharacterListUseCase
import com.pi.marvelapp.features.characterlist.domain.viewaction.CharacterListAction
import com.pi.marvelapp.features.characterlist.domain.viewstate.CharacterListViewState
import com.pi.marvelapp.features.characterlist.presentation.CharacterListFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val useCase: CharacterListUseCase
) : BaseViewModel<CharacterListViewState, CharacterListAction>(CharacterListViewState(null)) {

    private val navDirections = CharacterListFragmentDirections

    fun navigateToCharacterDetails(characterInfo: CharacterInfo) {


        val direction = navDirections.actionCharacterListFragmentToCharacterDetailFragment(
            name = characterInfo.name ?: STRING_EMPTY,
            description = characterInfo.description ?: STRING_EMPTY,
            thumbnailUrl = createThumbnailUrl(characterInfo.thumbnail),
            characterId = characterInfo.id ?: ZERO_INT
        )
        navigate(direction)
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
