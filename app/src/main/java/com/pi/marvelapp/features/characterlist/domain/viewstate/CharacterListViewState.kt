package com.pi.marvelapp.features.characterlist.domain.viewstate

import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewState
import com.pi.data.remote.response.Character

data class CharacterListViewState(
    val characterList: List<Character> = emptyList(),
    val character: Character? = null,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
