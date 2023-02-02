package com.example.dstvmovie.data.repository.impl

import com.example.dstvmovie.data.local.dao.MovieDao
import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.remote.api.ApiService
import com.example.dstvmovie.data.remote.response.BaseListResponse
import com.example.dstvmovie.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    @Named("io") private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getItemById(id: String): Item? =
    withContext(ioDispatcher) {
        movieDao.getItem(id = id)
    }

    override suspend fun getItemList(): BaseListResponse = withContext(ioDispatcher) {
        apiService.getDiscoverMovie()
    }

    override suspend fun insertItemsToDB(
        list: List<Item>
    ) = withContext(ioDispatcher) {
        movieDao.insert(list)
    }

    override suspend fun getItemListLocal(): List<Item>? = withContext(ioDispatcher) {
        movieDao.getItemList()
    }

    override suspend fun deleteAllItemsLocal() = withContext(ioDispatcher) {
        movieDao.deleteAll()
    }

}
