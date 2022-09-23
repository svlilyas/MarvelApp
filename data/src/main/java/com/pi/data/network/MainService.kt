package com.pi.data.network

import com.pi.data.remote.response.CharacterListResponse
import com.pi.data.utils.DataConstants.Companion.THIRTY_INT
import com.pi.data.utils.DataConstants.Companion.ZERO_INT
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    object Endpoint {
        const val mainPath = "v1/public"

        object Characters {
            const val characterMainPath = "${mainPath}/characters"
        }
    }

/*    @GET(Endpoint.Characters.characterMainPath)
    suspend fun fetchCharacterList(
        @Query("limit") limit: Int = THIRTY_INT,
        @Query("offset") offset: Int = ZERO_INT
    ): ApiResponse<CharacterListResponse>*/

    @GET(Endpoint.Characters.characterMainPath)
    suspend fun fetchCharacterList(
        @Query("limit") limit: Int = THIRTY_INT,
        @Query("offset") offset: Int = ZERO_INT
    ): CharacterListResponse
}
