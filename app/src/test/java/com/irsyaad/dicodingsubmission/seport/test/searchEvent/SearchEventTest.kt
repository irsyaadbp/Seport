package com.irsyaad.dicodingsubmission.seport.test.searchEvent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irsyaad.dicodingsubmission.seport.test.utils.getOrAwaitValue
import com.irsyaad.dicodingsubmission.seport.view.search.SearchViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchEventTest {
    private lateinit var viewModel: SearchViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        viewModel = SearchViewModel()
    }

    @Test
    fun searchEventTest(){
        viewModel.setDataSearch("Arsenal")
        val result = viewModel.getSearchEvent().getOrAwaitValue()
        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))

    }
}