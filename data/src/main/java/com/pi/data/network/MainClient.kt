package com.pi.data.network

import com.pi.data.remote.response.Character
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MainClient @Inject constructor(
    private val mainService: MainService
) {

    suspend fun fetchCharacterList(): ApiResponse<List<Character>> =
        mainService.fetchCharacterList()
}
