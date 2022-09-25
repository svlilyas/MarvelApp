package com.pi.marvelapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.pi.marvelapp.data.ComicsModelFactory
import com.pi.marvelapp.features.characterdetail.domain.usecase.CharacterDetailUseCase
import com.pi.marvelapp.features.characterdetail.domain.viewmodel.CharacterDetailViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Testing NoteListViewModel with real ROOM Database and looking if datas inserted, updated, deleted
 * or could be able to get all notes from ROOM
 */

class CharacterDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var characterDetailUseCase: CharacterDetailUseCase

    private lateinit var viewModel: CharacterDetailViewModel

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        viewModel = CharacterDetailViewModel(characterDetailUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get All Characters from Service (API) return list`(): TestResult = runTest {
        Mockito.`when`(
            characterDetailUseCase.fetchCharacterComics(
                limit = 10,
                characterId = 1,
                orderBy = "",
                dateRange = "",
                onStart = {},
                onComplete = {},
                onError = {})
        ).thenReturn(flow { emit(ComicsModelFactory.getMockComicResponse()) })

        viewModel.fetchCharacterComics(
            limit = 10,
            characterId = 1,
            orderBy = "",
            dateRange = ""
        )
        var count = 0

        viewModel.uiStateFlow.test {
            val comicList = awaitItem().comicList

            if (count == 1) {
                assertThat(comicList).isNotNull()
                assertEquals(comicList, ComicsModelFactory.getMockComicList())
            }
            count++
        }
    }
}
