package com.pi.marvelapp.features.characterdetail.domain.viewaction

import com.pi.data.remote.response.CharacterComicsResponse
import com.pi.marvelapp.core.platform.viewmodel.BaseAction

sealed class CharacterDetailAction : BaseAction {
    object GetComicListLoading : CharacterDetailAction()
    class GetComicListSuccess(val comicList: List<CharacterComicsResponse.Data.Result>) :
        CharacterDetailAction()

    object GetComicListFailure : CharacterDetailAction()
}
