package com.pi.marvelapp.data

import com.pi.data.remote.response.CharacterResponse

object NoteModelFactory {

    private fun getMockNote(): CharacterResponse = CharacterResponse(
        id = 0,
        title = "",
        description = "",
        createdAt = 0,
        modifiedAt = 0,
        imageUrl = "",
    )

    fun getMockNotes(): List<CharacterResponse> {
        val characterResponseList = ArrayList<CharacterResponse>()
        repeat(5) {
            characterResponseList.add(getMockNote())
        }
        return characterResponseList
    }
}
