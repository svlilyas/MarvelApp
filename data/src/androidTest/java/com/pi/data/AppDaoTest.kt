package com.pi.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.pi.data.remote.response.CharacterResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AppDaoTest {
    private lateinit var appDao: AppDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).build()
        appDao = db.appDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun testWriteNoteAndReadInList(): Unit = runBlocking {
        //arrange
        val fakeTitle = "Title"
        val fakeDescription = "Description"
        val fakeImageUrl = "ImageUrl"
        val fakeCharacterResponse =
            CharacterResponse(title = fakeTitle, description = fakeDescription, imageUrl = fakeImageUrl)

        //act
        appDao.insert(fakeCharacterResponse)
        val lastNote = appDao.getAllCharacters().first()

        //assert
        assertThat(lastNote.title).isEqualTo(fakeTitle)
        assertThat(lastNote.description).isEqualTo(fakeDescription)
        assertThat(lastNote.imageUrl).isEqualTo(fakeImageUrl)
    }

    @Test
    @Throws(Exception::class)
    fun testUpdateNoteAndReadInList(): Unit = runBlocking {
        //arrange
        val fakeTitle = "Title"
        val fakeDescription = "Description"
        val fakeImageUrl = "ImageUrl"
        val fakeCharacterResponse =
            CharacterResponse(title = fakeTitle, description = fakeDescription, imageUrl = fakeImageUrl)

        //act
        appDao.insert(fakeCharacterResponse)
        val lastNote = appDao.getAllCharacters().first()

        //update note data
        val fakeUpdatedTitle = "UpdatedTitle"
        val fakeUpdatedDescription = "UpdatedDescription"
        val fakeUpdatedImageUrl = "UpdatedImageUrl"
        val fakeUpdatedNote = lastNote.copy(
            title = fakeUpdatedTitle,
            description = fakeUpdatedDescription,
            imageUrl = fakeUpdatedImageUrl
        )
        appDao.updateWithTimeStamp(characterResponse = fakeUpdatedNote)
        val lastNoteUpdated = appDao.getAllCharacters().first()

        //assert Updated Note
        assertThat(lastNoteUpdated.title).isEqualTo(fakeUpdatedTitle)
        assertThat(lastNoteUpdated.description).isEqualTo(fakeUpdatedDescription)
        assertThat(lastNoteUpdated.imageUrl).isEqualTo(fakeUpdatedImageUrl)
    }

    @Test
    @Throws(Exception::class)
    fun testDeleteNoteRemovesNoteFromList(): Unit = runBlocking {
        //arrange
        val fakeTitle = "Title"
        val fakeDescription = "Description"
        val fakeImageUrl = "ImageUrl"
        val fakeCharacterResponse =
            CharacterResponse(title = fakeTitle, description = fakeDescription, imageUrl = fakeImageUrl)

        //act
        appDao.insert(fakeCharacterResponse)
        val lastNote = appDao.getAllCharacters().first()

        //delete note data
        appDao.delete(characterResponse = lastNote)
        val updatedNoteList = appDao.getAllCharacters()

        //assert deleted note
        assertThat(updatedNoteList).isEmpty()
    }
}