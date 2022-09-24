package com.pi.marvelapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pi.marvelapp.features.characterdetail.domain.usecase.CharacterListUseCase
import com.pi.marvelapp.features.characterdetail.domain.viewmodel.CharacterListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Testing NoteListViewModel with real ROOM Database and looking if datas inserted, updated, deleted
 * or could be able to get all notes from ROOM
 */

class CharacterListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var characterListUseCase: CharacterListUseCase

    private lateinit var viewModel: CharacterListViewModel

    private val mainThreadSurrogate = newSingleThreadContext("Main Thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)
        viewModel = CharacterListViewModel(characterListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /*@Test
    fun `Get All Notes Data from usecase (ROOM) return list`(): TestResult = runBlocking {
        Mockito.`when`(characterListUseCase.fetchAllCharacters())
            .thenReturn(flow { emit(NoteModelFactory.getMockNotes()) })

        viewModel.fetchAllCharacters()

        val noteList = viewModel.noteList.getOrAwaitValue(time = 10)

        assertThat(noteList).isNotNull()
        assertThat(noteList).isNotEmpty()
        assertEquals(noteList, NoteModelFactory.getMockNotes())
    }*/
}
