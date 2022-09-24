package com.pi.marvelapp.data

import com.pi.data.remote.response.CharacterComicsResponse
import com.pi.data.remote.response.CharacterListResponse

object ComicsModelFactory {

    fun getMockComicResponse(): CharacterComicsResponse = CharacterComicsResponse(
        attributionHTML = null,
        attributionText = null,
        code = null,
        copyright = null,
        data = CharacterComicsResponse.Data(
            count = 10,
            limit = 10,
            offset = 0,
            results = getMockComicList(),
            total = 10
        ),
        etag = null,
        status = null
    )


    fun getMockComicList(): List<CharacterComicsResponse.Data.Result> {
        val characterResponseList = ArrayList<CharacterComicsResponse.Data.Result>()
        repeat(5) {
            characterResponseList.add(getMockComic())
        }
        return characterResponseList
    }

    fun getMockComic(): CharacterComicsResponse.Data.Result = CharacterComicsResponse.Data.Result(
        characters = null,
        collectedIssues = listOf(),
        collections = listOf(),
        creators = null,
        dates = listOf(),
        description = "",
        diamondCode = null,
        digitalId = null,
        ean = null,
        events = null,
        format = null,
        id = null,
        images = listOf(),
        isbn = null,
        issn = null,
        issueNumber = null,
        modified = null,
        pageCount = null,
        prices = listOf(),
        resourceURI = null,
        series = null,
        stories = null,
        textObjects = listOf(),
        thumbnail = CharacterListResponse.Data.CharacterInfo.Thumbnail("ss", "ss"),
        title = "sss",
        upc = null,
        urls = listOf(),
        variantDescription = null,
        variants = listOf()
    )
}
