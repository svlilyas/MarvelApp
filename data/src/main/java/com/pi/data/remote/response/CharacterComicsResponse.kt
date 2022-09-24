package com.pi.data.remote.response

import com.squareup.moshi.Json

data class CharacterComicsResponse(
    val attributionHTML: String?, // <a href="http://marvel.com">Data provided by Marvel. © 2022 MARVEL</a>
    val attributionText: String?, // Data provided by Marvel. © 2022 MARVEL
    val code: Int?, // 200
    val copyright: String?, // © 2022 MARVEL
    @Json(name = "data")
    val data: Data?,
    val etag: String?, // a723daee9af96422d0b2992a796a73c53708e822
    val status: String? // Ok
) {
    data class Data(
        val count: Int?, // 10
        val limit: Int?, // 10
        val offset: Int?, // 0
        val results: List<Result> = emptyList(),
        val total: Int? // 12
    ) {
        data class Result(
            val characters: Characters?,
            val collectedIssues: List<Any> = emptyList(),
            val collections: List<Any> = emptyList(),
            val creators: Creators?,
            val dates: List<Date> = emptyList(),
            val description: String?, // Join 3-D MAN, CLOUD 9, KOMODO, HARDBALL, and heroes around America in the battle that will decide the fate of the planet and the future of the Initiative program. Will the Kill Krew Army win the day?
            val diamondCode: String?, // SEP082362
            val digitalId: Int?, // 10949
            val ean: String?,
            val events: Events?,
            val format: String?, // Comic
            val id: Int?, // 22506
            val images: List<Image> = emptyList(),
            val isbn: String?,
            val issn: String?,
            val issueNumber: Int?, // 19
            val modified: String?, // 2015-10-27T16:38:23-0400
            val pageCount: Int?, // 32
            val prices: List<Price> = emptyList(),
            val resourceURI: String?, // http://gateway.marvel.com/v1/public/comics/22506
            val series: Series?,
            val stories: Stories?,
            val textObjects: List<TextObject> = emptyList(),
            val thumbnail: CharacterListResponse.Data.CharacterInfo.Thumbnail?,
            val title: String?, // Avengers: The Initiative (2007) #19
            val upc: String?, // 5960606084-01911
            val urls: List<Url> = emptyList(),
            val variantDescription: String?,
            val variants: List<Variant> = emptyList()
        ) {
            data class Characters(
                val available: Int?, // 9
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/comics/22506/characters
                val items: List<Item> = emptyList(),
                val returned: Int? // 9
            ) {
                data class Item(
                    val name: String?, // 3-D Man
                    val resourceURI: String? // http://gateway.marvel.com/v1/public/characters/1011334
                )
            }

            data class Creators(
                val available: Int?, // 9
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/comics/22506/creators
                val items: List<Item> = emptyList(),
                val returned: Int? // 9
            ) {
                data class Item(
                    val name: String?, // Tom Brevoort
                    val resourceURI: String?, // http://gateway.marvel.com/v1/public/creators/2133
                    val role: String? // editor
                )
            }

            data class Date(
                val date: String?, // 2008-12-17T00:00:00-0500
                val type: String? // onsaleDate
            )

            data class Events(
                val available: Int?, // 1
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/comics/22506/events
                val items: List<Item> = emptyList(),
                val returned: Int? // 1
            ) {
                data class Item(
                    val name: String?, // Secret Invasion
                    val resourceURI: String? // http://gateway.marvel.com/v1/public/events/269
                )
            }

            data class Image(
                val extension: String?, // jpg
                val path: String? // http://i.annihil.us/u/prod/marvel/i/mg/d/03/58dd080719806
            )

            data class Price(
                val price: Double?, // 2.99
                val type: String? // printPrice
            )

            data class Series(
                val name: String?, // Avengers: The Initiative (2007 - 2010)
                val resourceURI: String? // http://gateway.marvel.com/v1/public/series/1945
            )

            data class Stories(
                val available: Int?, // 2
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/comics/22506/stories
                val items: List<Item> = emptyList(),
                val returned: Int? // 2
            ) {
                data class Item(
                    val name: String?, // AVENGERS: THE INITIATIVE (2007) #19
                    val resourceURI: String?, // http://gateway.marvel.com/v1/public/stories/49888
                    val type: String? // cover
                )
            }

            data class TextObject(
                val language: String?, // en-us
                val text: String?, // Join 3-D MAN, CLOUD 9, KOMODO, HARDBALL, and heroes around America in the battle that will decide the fate of the planet and the future of the Initiative program. Will the Kill Krew Army win the day?
                val type: String? // issue_preview_text
            )

            data class Url(
                val type: String?, // detail
                val url: String? // http://marvel.com/comics/issue/22506/avengers_the_initiative_2007_19?utm_campaign=apiRef&utm_source=781ef9ef74b9ff4d6134ea69cdd8e190
            )

            data class Variant(
                val name: String?, // Avengers: The Initiative (2007) #18 (ZOMBIE VARIANT)
                val resourceURI: String? // http://gateway.marvel.com/v1/public/comics/22300
            )
        }
    }
}