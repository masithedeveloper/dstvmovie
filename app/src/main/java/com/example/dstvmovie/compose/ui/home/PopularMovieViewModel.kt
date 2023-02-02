package com.example.dstvmovie.compose.ui.home

import androidx.lifecycle.viewModelScope
import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.repository.MovieRepository
import com.example.dstvmovie.compose.ui.base.BaseLoadMoreRefreshViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseLoadMoreRefreshViewModel<Item>() {
    override fun loadData(page: Int) {
        viewModelScope.launch {
            try {
                val results = movieRepository.getItemList()
                onLoadSuccess(0, results.components?.get(1)?.items)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }
}
