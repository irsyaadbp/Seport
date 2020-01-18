package com.irsyaad.dicodingsubmission.seport.test.pastEvent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irsyaad.dicodingsubmission.seport.test.utils.getOrAwaitValue
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PastEventTest {
    private lateinit var viewModel: DetailEventViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailEventViewModel()
    }

    @Test
    fun getNextEventTest() {
        val result = viewModel.getPastEvent(4328).getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))
    }
}