package com.example.dstvmovie.data.repository

import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.remote.response.BaseListResponse

interface MovieRepository {

    suspend fun getItemById(id: String): Item?

    suspend fun getItemList(): BaseListResponse

    suspend fun insertItemsToDB(list: List<Item>)

    suspend fun getItemListLocal(): List<Item>?

    suspend fun deleteAllItemsLocal()
    suspend fun getItemsByOrder(filterByRank: Int): List<Item>?
}
