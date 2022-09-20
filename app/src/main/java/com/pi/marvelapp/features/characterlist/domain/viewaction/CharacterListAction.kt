package com.pi.marvelapp.features.characterlist.domain.viewaction

import com.pi.marvelapp.core.platform.viewmodel.BaseAction
import com.pi.data.remote.response.Character

sealed class CharacterListAction : BaseAction {
    class GetCharacterListSuccess(val characterList: List<Character>) : CharacterListAction()
    object GetCharacterListFailure : CharacterListAction()
}
