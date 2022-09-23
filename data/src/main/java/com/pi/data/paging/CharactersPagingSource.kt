package com.pi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pi.data.network.MainService
import com.pi.data.remote.response.CharacterListResponse.Data.CharacterInfo
import com.pi.data.utils.DataConstants.Companion.ONE_INT
import com.pi.data.utils.DataConstants.Companion.ZERO_INT

class CharactersPagingSource(private val mainService: MainService, private val limit: Int) :
    PagingSource<Int, CharacterInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterInfo> {
        return try {
            val offset = params.key ?: ZERO_INT

            val response =
                mainService.fetchCharacterList(limit = limit, offset = offset)

            LoadResult.Page(
                data = response.data.results,
                prevKey = if (offset == ZERO_INT) null else offset.minus(limit),
                nextKey = if (offset.compareTo(
                        response.data.total ?: ZERO_INT
                    ) == ONE_INT
                ) null else offset.plus(limit)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(limit) ?: anchorPage?.nextKey?.minus(limit)
        }
    }
}