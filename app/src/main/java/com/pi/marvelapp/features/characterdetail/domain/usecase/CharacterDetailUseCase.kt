package com.pi.marvelapp.features.characterdetail.domain.usecase

import com.pi.data.repository.MainRepository
import javax.inject.Inject

class CharacterDetailUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun fetchCharacterComics(
        limit: Int,
        characterId: Int,
        orderBy: String,
        dateRange: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = mainRepository.fetchCharacterComics(
        limit = limit,
        characterId = characterId,
        orderBy = orderBy,
        dateRange = dateRange,
        onStart = onStart,
        onComplete = onComplete,
        onError = onError
    )
}
