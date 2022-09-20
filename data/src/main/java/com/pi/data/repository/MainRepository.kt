package com.pi.data.repository

import androidx.annotation.WorkerThread
import com.pi.data.network.MainClient
import com.pi.data.persistence.AppDao
import com.pi.data.remote.response.Character
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainClient: MainClient,
    private val appDao: AppDao,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun fetchAllCharacters(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var characters = appDao.getAllCharacters()
        if (characters.isEmpty()) {
            /**
             * fetches a list of [Character] from the network and getting [ApiResponse] asynchronously.
             * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#apiresponse-extensions-for-coroutines)
             */
            val response = mainClient.fetchCharacterList()
            response.suspendOnSuccess {
                characters = data
                appDao.insertCharacterList(data)
                emit(appDao.getAllCharacters())
            }.onFailure { // handles the all error cases from the API request fails.
                onError(message())
            }
        } else {
            emit(appDao.getAllCharacters())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


    @WorkerThread
    fun insertNote(
        character: Character,
        onComplete: () -> Unit
    ): Flow<Unit> = flow {
        emit(appDao.insert(character = character))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    fun updateNote(
        character: Character,
        onComplete: () -> Unit
    ): Flow<Unit> = flow {
        emit(appDao.updateWithTimeStamp(character = character))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

    @WorkerThread
    fun deleteNote(
        character: Character,
        onComplete: () -> Unit
    ): Flow<Int> = flow {
        emit(appDao.delete(character = character))
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
