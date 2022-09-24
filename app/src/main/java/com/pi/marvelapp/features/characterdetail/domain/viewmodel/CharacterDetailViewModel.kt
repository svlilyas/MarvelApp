package com.pi.marvelapp.features.characterdetail.domain.viewmodel

import androidx.lifecycle.viewModelScope
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewModel
import com.pi.marvelapp.core.utils.AppConstants.Companion.DEFAULT_ORDER_BY
import com.pi.marvelapp.core.utils.AppConstants.Companion.TEN_INT
import com.pi.marvelapp.features.characterdetail.domain.usecase.CharacterDetailUseCase
import com.pi.marvelapp.features.characterdetail.domain.viewaction.CharacterDetailAction
import com.pi.marvelapp.features.characterdetail.domain.viewstate.CharacterDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val useCase: CharacterDetailUseCase
) : BaseViewModel<CharacterDetailViewState, CharacterDetailAction>(
    CharacterDetailViewState()
) {
    fun fetchCharacterComics(
        limit: Int?,
        characterId: Int,
        orderBy: String?,
        dateRange: String
    ) {

        viewModelScope.launch {

            useCase.fetchCharacterComics(
                limit = limit ?: TEN_INT,
                characterId = characterId,
                orderBy = orderBy ?: DEFAULT_ORDER_BY,
                dateRange = dateRange,
                onStart = { sendAction(CharacterDetailAction.GetComicListLoading) },
                onComplete = {},
                onError = { sendAction(CharacterDetailAction.GetComicListFailure) })
                .collectLatest {
                    val comicList = it.data?.results
                    if (comicList?.isNotEmpty() == true) {
                        sendAction(CharacterDetailAction.GetComicListSuccess(comicList))
                    } else {
                        sendAction(CharacterDetailAction.GetComicListSuccess(emptyList()))
                    }
                }
        }
    }

    override fun onReduceState(viewAction: CharacterDetailAction): CharacterDetailViewState =
        when (viewAction) {
            CharacterDetailAction.GetComicListLoading -> state.copy(
                uiState = UiState.LOADING
            )
            is CharacterDetailAction.GetComicListSuccess -> state.copy(
                comicList = viewAction.comicList,
                uiState = UiState.SUCCESS
            )
            CharacterDetailAction.GetComicListFailure -> state.copy(
                uiState = UiState.ERROR
            )
        }
}
