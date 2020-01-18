package com.irsyaad.dicodingsubmission.seport.test.detailEvent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport
import com.irsyaad.dicodingsubmission.seport.test.utils.getOrAwaitValue
import com.irsyaad.dicodingsubmission.seport.view.detail.DetailEventViewModel
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailEventTest {
    private lateinit var viewModelDetail: DetailEventViewModel
    private lateinit var expectedData: SportModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        expectedData = DataSport.listDataSport[0]
        viewModelDetail = DetailEventViewModel()
    }

    @Test
    fun getDetailEventTest(){
        val result = viewModelDetail.getDetailEventLeague(441632).getOrAwaitValue()

        MatcherAssert.assertThat(result, Matchers.not(Matchers.nullValue()))
    }

    @Test
    fun getDetailHomeTeamTest(){
        val homeTeam = viewModelDetail.getHomeTeam(133604).getOrAwaitValue()

        MatcherAssert.assertThat(homeTeam, Matchers.not(Matchers.nullValue()))
    }

    @Test
    fun getDetailAwayTeamTest(){
        val awayTeam = viewModelDetail.getAwayTeam(133609).getOrAwaitValue()

        MatcherAssert.assertThat(awayTeam, Matchers.not(Matchers.nullValue()))
    }
}