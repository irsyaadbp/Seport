package com.irsyaad.dicodingsubmission.seport.test.detailLeague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport
import com.irsyaad.dicodingsubmission.seport.test.utils.getOrAwaitValue
import com.irsyaad.dicodingsubmission.seport.view.detail.DetaiLeagueViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailLeagueTest {
    private lateinit var viewModel: DetaiLeagueViewModel
    private lateinit var expectedData: SportModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        expectedData = DataSport.listDataSport[0]
        viewModel =
            DetaiLeagueViewModel()
    }

    @Test
    fun getDetailLeagueTest(){
        val result = viewModel.getDetailLeague(4328).getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))
    }
}