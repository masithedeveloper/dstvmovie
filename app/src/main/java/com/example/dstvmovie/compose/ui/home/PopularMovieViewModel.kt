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
                if(results.components?.isNotEmpty() == true)
                {
                    results.components.first().items?.let {
                        valueToOrderBy.value = it[1].valueToOrderBy
                    }
                    results.components[1].items?.let {
                        movieRepository.insertItemsToDB(it)
                    }
                    onLoadSuccess(0, movieRepository.getItemsByOrder(filterByRank()))
                }
                else{
                    isEmptyList.value = true
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun filterByRank(): Int =  if (valueToOrderBy.value.toString() == "rank") {1} else {2}
}
