package com.pi.marvelapp.features.characterlist.domain.viewstate

import androidx.paging.PagingData
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.common.data.UiState
import com.pi.marvelapp.core.platform.viewmodel.BaseViewState

data class CharacterListViewState(
    val characterList: PagingData<CharacterInfo>?,
    override val uiState: UiState = UiState.LOADING
) : BaseViewState
