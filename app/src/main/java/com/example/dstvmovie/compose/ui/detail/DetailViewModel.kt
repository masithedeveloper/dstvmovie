package com.example.dstvmovie.compose.ui.detail

import androidx.lifecycle.viewModelScope
import com.example.dstvmovie.compose.ui.base.StateViewModel
import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : StateViewModel() {
    private val _item by lazy { MutableStateFlow<Item?>(null) }
    val item: StateFlow<Item?> = _item

    private var itemId: String? = null

    fun setValueMovieId(id: String?) {
        itemId = id
    }

    fun getMovieDetail(itemId: String?) {
        if (itemId.isNullOrBlank()) return
        viewModelScope.launch {
            try {
                showLoading()
                _item.value = movieRepository.getItemById(id = itemId)
            } catch (e: Exception) {
                onError(e)
            } finally {
                hideLoading()
            }
        }
    }

    override fun doRefresh() {
        itemId?.let { id ->
            if (itemId.isNullOrBlank()) return
            viewModelScope.launch {
                try {
                    showRefreshing()
                    _item.value = movieRepository.getItemById(id = id)
                } catch (e: Exception) {
                    onError(e)
                } finally {
                    hideRefreshing()
                }
            }
        }
    }
}
