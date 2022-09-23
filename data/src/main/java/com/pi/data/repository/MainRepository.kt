package com.pi.data.repository

import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pi.data.network.MainClient
import com.pi.data.network.MainService
import com.pi.data.paging.CharactersPagingSource
import com.pi.data.remote.response.CharacterListResponse
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainClient: MainClient,
    private val mainService: MainService,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    /*@WorkerThread
    fun fetchAllCharacters(
        limit: Int,
        offset: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        *//**
         * fetches a list of [CharacterListResponse] from the network and getting [ApiResponse] asynchronously.
         * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
         *//*
        val response = mainClient.fetchCharacterList(limit = limit, offset = offset)
        var characterList = emptyList<CharacterInfo>()
        response.suspendOnSuccess {
            data.data.results.let {
                if (it.isNotEmpty()) {
                    characterList = it
                }
                emit(characterList)
            }
        }.onFailure { // handles the all error cases from the API request fails.
            onError(message())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)*/

    @WorkerThread
    fun fetchAllCharacters(
        limit: Int
    ): Flow<PagingData<CharacterInfo>> =
        Pager(config = PagingConfig(
            pageSize = limit,
            enablePlaceholders = false
        ),
            pagingSourceFactory = {
                CharactersPagingSource(
                    mainService = mainService,
                    limit = limit
                )
            }).flow
}
