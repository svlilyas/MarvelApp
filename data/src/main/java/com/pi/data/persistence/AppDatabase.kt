package com.pi.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pi.data.remote.response.Character

@Database(entities = [Character::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}
