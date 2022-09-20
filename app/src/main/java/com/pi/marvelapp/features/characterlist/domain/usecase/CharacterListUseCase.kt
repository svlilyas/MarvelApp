package com.pi.marvelapp.features.characterlist.domain.usecase

import com.pi.data.repository.MainRepository
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(private val mainRepository: MainRepository) {

    fun fetchAllCharacters() = mainRepository.fetchAllCharacters({}, {}) {}
}
