package com.pi.data.persistence

import androidx.room.*
import com.pi.data.remote.response.Character

@Dao
interface AppDao {

    /**
     * characters_db Operations
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characterList: List<Character>)

    /**
     * @param character Updating
     */
    @Update
    suspend fun update(character: Character)

    /**
     * @param character Updating with timestamp which means modified timestamp
     */
    suspend fun updateWithTimeStamp(character: Character) {
        update(
            character.copy(modifiedAt = System.currentTimeMillis())
        )
    }

    /**
     * @param character Deleting
     */
    @Delete
    suspend fun delete(character: Character): Int

    /**
     * Getting all notes in database
     */
    @Query("SELECT * FROM characters_db")
    suspend fun getAllCharacters(): List<Character>
}
