package com.irsyaad.dicodingsubmission.seport.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport

class MainViewModel : ViewModel(){
    private val listLeague: MutableLiveData<ArrayList<SportModel>> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getListLeague(): LiveData<ArrayList<SportModel>> {
        if(listLeague.value == null) listLeague.postValue(DataSport.listDataSport)
        return listLeague
    }
}