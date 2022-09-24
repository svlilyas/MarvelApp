package com.pi.marvelapp.features.characterdetail.domain.viewstate

import com.pi.data.remote.response.CharacterComicsResponse
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewState

data class CharacterDetailViewState(
    val comicList: List<CharacterComicsResponse.Data.Result> = emptyList(),
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
