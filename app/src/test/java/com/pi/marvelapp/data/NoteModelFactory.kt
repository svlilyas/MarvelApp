package com.pi.marvelapp.data

import com.pi.data.remote.response.Character

object NoteModelFactory {

    private fun getMockNote(): Character = Character(
        id = 0,
        title = "",
        description = "",
        createdAt = 0,
        modifiedAt = 0,
        imageUrl = "",
    )

    fun getMockNotes(): List<Character> {
        val characterList = ArrayList<Character>()
        repeat(5) {
            characterList.add(getMockNote())
        }
        return characterList
    }
}
