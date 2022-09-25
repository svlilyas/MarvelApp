package com.pi.data.remote.response

import com.squareup.moshi.Json

data class CharacterListResponse(
    val attributionHTML: String?, // <a href="http://marvel.com">Data provided by Marvel. © 2022 MARVEL</a>
    val attributionText: String?, // Data provided by Marvel. © 2022 MARVEL
    val code: Int?, // 200
    val copyright: String?, // © 2022 MARVEL
    @Json(name = "data")
    val data: Data,
    val etag: String?, // 532f8baa0d3f5f1b05674fd910df0c20c4f7090c
    val status: String? // Ok
) {

    data class Data(
        val count: Int?, // 30
        val limit: Int?, // 30
        val offset: Int?, // 0
        val results: List<CharacterInfo> = emptyList(),
        val total: Int? // 1562
    ) {
        data class CharacterInfo(
            val comics: Comics?,
            val description: String?,
            val events: Events?,
            val id: Int?, // 1011334
            val modified: String?, // 2014-04-29T14:18:17-0400
            val name: String?, // 3-D Man
            val resourceURI: String?, // http://gateway.marvel.com/v1/public/characters/1011334
            val series: Series?,
            val stories: Stories?,
            val thumbnail: Thumbnail?,
            val urls: List<Url> = emptyList()
        ) {
            data class Comics(
                val available: Int?, // 12
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/characters/1011334/comics
                val items: List<Item> = emptyList(),
                val returned: Int? // 12
            ) {
                data class Item(
                    val name: String?, // Avengers: The Initiative (2007) #14
                    val resourceURI: String? // http://gateway.marvel.com/v1/public/comics/21366
                )
            }

            data class Events(
                val available: Int?, // 1
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/characters/1011334/events
                val items: List<Item> = emptyList(),
                val returned: Int? // 1
            ) {
                data class Item(
                    val name: String?, // Secret Invasion
                    val resourceURI: String? // http://gateway.marvel.com/v1/public/events/269
                )
            }

            data class Series(
                val available: Int?, // 3
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/characters/1011334/series
                val items: List<Item> = emptyList(),
                val returned: Int? // 3
            ) {
                data class Item(
                    val name: String?, // Avengers: The Initiative (2007 - 2010)
                    val resourceURI: String? // http://gateway.marvel.com/v1/public/series/1945
                )
            }

            data class Stories(
                val available: Int?, // 21
                val collectionURI: String?, // http://gateway.marvel.com/v1/public/characters/1011334/stories
                val items: List<Item> = emptyList(),
                val returned: Int? // 20
            ) {
                data class Item(
                    val name: String?, // Cover #19947
                    val resourceURI: String?, // http://gateway.marvel.com/v1/public/stories/19947
                    val type: String? // cover
                )
            }

            data class Thumbnail(
                val extension: String?, // jpg
                val path: String? // http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784
            )

            data class Url(
                val type: String?, // detail
                val url: String? // http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=781ef9ef74b9ff4d6134ea69cdd8e190
            )
        }
    }
}