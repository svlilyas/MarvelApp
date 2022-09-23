package com.pi.data.network

import com.pi.data.remote.response.CharacterListResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MainClient @Inject constructor(
    private val mainService: MainService
) {

    /*suspend fun fetchCharacterList(limit: Int, offset: Int): ApiResponse<CharacterListResponse> =
        mainService.fetchCharacterList(limit = limit, offset = offset)*/
}
