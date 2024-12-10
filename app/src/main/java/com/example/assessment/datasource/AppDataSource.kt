package com.example.assessment.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assessment.model.Item
import com.example.assessment.network.GeneralApi
import com.example.assessment.network.RetrofitHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppDataSource : PagingSource<Int, Item>() {
    private val api: GeneralApi = RetrofitHandler.getInstance().create(GeneralApi::class.java)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val currentPage = params.key ?: 1
            var data = api.getItems()
            data = data.sortedByDescending { it.timestamp }

            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

interface AppApi {
    suspend fun fetchItemFlow(): List<Item>
}