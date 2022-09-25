package com.pi.data.network

import com.pi.data.remote.response.CharacterComicsResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MainClient @Inject constructor(
    private val mainService: MainService
) {

    suspend fun fetchCharacterComics(
        limit: Int,
        characterId: Int,
        orderBy: String,
        dateRange:String
    ): ApiResponse<CharacterComicsResponse> =
        mainService.fetchCharacterComics(
            limit = limit,
            characterId = characterId,
            orderBy = orderBy,
            dateRange = dateRange
        )
}
