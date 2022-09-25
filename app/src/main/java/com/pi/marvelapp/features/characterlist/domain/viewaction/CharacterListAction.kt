package com.pi.marvelapp.features.characterlist.domain.viewaction

import androidx.paging.PagingData
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.marvelapp.core.platform.viewmodel.BaseAction

sealed class CharacterListAction : BaseAction {
    object GetCharacterListLoading : CharacterListAction()
    class GetCharacterListSuccess(val characterList: PagingData<CharacterInfo>) :
        CharacterListAction()

    object GetCharacterListFailure : CharacterListAction()
}
