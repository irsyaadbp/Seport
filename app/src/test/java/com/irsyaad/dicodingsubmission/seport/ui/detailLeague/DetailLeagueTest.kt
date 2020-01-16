package com.irsyaad.dicodingsubmission.seport.ui.detailLeague

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import com.irsyaad.dicodingsubmission.seport.mock
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailLeague
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport
import com.irsyaad.dicodingsubmission.seport.viewModel.ListViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)

class DetailLeagueTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: ListViewModel


    private val observer: Observer<ListDetailLeague> = mock()

    @Before
    fun setUp(){
        val application = ApplicationProvider.getApplicationContext<Application>()

        viewModel = ListViewModel(application)

        viewModel.detailLeagues.observeForever(observer)
    }

    @Test
    fun getDetailLeague(){
        val expectedLeague = DataSport.listDataSport[0]
        viewModel.setDataDetailLeague(4328)
        val captor = ArgumentCaptor.forClass(ListDetailLeague::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(expectedLeague.id, value.idLeague)
            assertEquals(expectedLeague.description, value.strDescriptionEN)
        }
    }
}