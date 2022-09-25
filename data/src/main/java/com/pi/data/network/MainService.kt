package com.pi.data.network

import com.pi.data.remote.response.CharacterComicsResponse
import com.pi.data.remote.response.CharacterListResponse
import com.pi.data.utils.DataConstants.Companion.THIRTY_INT
import com.pi.data.utils.DataConstants.Companion.ZERO_INT
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {
    object Endpoint {
        const val mainPath = "v1/public"

        object Characters {
            const val characterMainPath = "${mainPath}/characters"

            const val characterComics = "${characterMainPath}/{characterId}/comics"
        }
    }

    @GET(Endpoint.Characters.characterComics)
    suspend fun fetchCharacterComics(
        @Path("characterId") characterId: Int,
        @Query("orderBy") orderBy: String,
        @Query("limit") limit: Int,
        @Query("dateRange") dateRange: String,
    ): ApiResponse<CharacterComicsResponse>

    @GET(Endpoint.Characters.characterMainPath)
    suspend fun fetchCharacterList(
        @Query("limit") limit: Int = THIRTY_INT,
        @Query("offset") offset: Int = ZERO_INT
    ): CharacterListResponse
}
