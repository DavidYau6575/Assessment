package com.example.assessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assessment.datasource.AppDataSource
import com.example.assessment.model.Item
import kotlinx.coroutines.flow.Flow


class ItemViewModel : ViewModel() {
    val pagedItems: Flow<PagingData<Item>> = Pager(PagingConfig(pageSize = 5)) {
        AppDataSource()
    }.flow.cachedIn(viewModelScope)

}