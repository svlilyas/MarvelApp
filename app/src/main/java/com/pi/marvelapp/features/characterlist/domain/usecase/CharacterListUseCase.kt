package com.pi.marvelapp.features.characterlist.domain.usecase

import com.pi.data.repository.MainRepository
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(private val mainRepository: MainRepository) {

    /*fun fetchAllCharacters(
        limit: Int,
        offset: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = mainRepository.fetchAllCharacters(
        limit = limit,
        offset = offset,
        onStart = onStart,
        onComplete = onComplete,
        onError = onError
    )*/

    fun fetchAllCharacters(
        limit: Int
    ) = mainRepository.fetchAllCharacters(
        limit = limit
    )
}
