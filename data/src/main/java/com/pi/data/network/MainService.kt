package com.pi.data.network

import com.pi.data.remote.response.Character
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface MainService {
    object Endpoint {
        const val mainPath = "/v1/public"

        object Characters {
            const val characterMainPath = "${mainPath}/characters"
        }
    }

    @GET(Endpoint.Characters.characterMainPath)
    suspend fun fetchCharacterList(): ApiResponse<List<Character>>
}
