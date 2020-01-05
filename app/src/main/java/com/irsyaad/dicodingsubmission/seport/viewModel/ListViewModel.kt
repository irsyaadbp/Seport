package com.irsyaad.dicodingsubmission.seport.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.Sport
import com.irsyaad.dicodingsubmission.seport.model.dataSport

class ListViewModel() : ViewModel() {

    private val listLeague: MutableLiveData<ArrayList<Sport>> = MutableLiveData()

    fun getDataFilm(): LiveData<ArrayList<Sport>> {
        if(listLeague.value == null) listLeague.postValue(dataSport.listDataSport)
        return listLeague
    }
}