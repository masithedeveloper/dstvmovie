package com.example.dstvmovie.data.ui.viewmodels

import androidx.lifecycle.Observer
import com.example.dstvmovie.compose.ui.home.PopularMovieViewModel
import com.example.dstvmovie.data.mock
import com.example.dstvmovie.data.mockdata.createBaseListResponse
import com.example.dstvmovie.data.model.Item
import com.example.dstvmovie.data.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class PopularMovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: PopularMovieViewModel

    private val movieRepository = mock<MovieRepository>()

    @Before
    override fun setup() {
        super.setup()
        viewModel = PopularMovieViewModel(movieRepository)
    }

    @Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            // given
            val fakeData = createBaseListResponse()
            val observer = mock<Observer<List<Item>>>()
            viewModel.itemList.observeForever(observer)

            `when`(movieRepository.getItemList()).thenReturn(fakeData)

            // when
            viewModel.loadData(0)

            // then
            Assert.assertEquals(4, viewModel.itemList.value?.size)
            Assert.assertEquals(1, viewModel.itemList.value?.getOrNull(0)?.id)
            Assert.assertEquals(2, viewModel.itemList.value?.getOrNull(1)?.id)
            Assert.assertEquals(3, viewModel.itemList.value?.getOrNull(2)?.id)
            Assert.assertEquals(4, viewModel.itemList.value?.getOrNull(3)?.id)

            verify(observer).onChanged(fakeData.components?.get(0)?.items)
        }
    }
}
