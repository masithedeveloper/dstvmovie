package com.example.dstvmovie.data.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dstvmovie.data.rules.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }
}
