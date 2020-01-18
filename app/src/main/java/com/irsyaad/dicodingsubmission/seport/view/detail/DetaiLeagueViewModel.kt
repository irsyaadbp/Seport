package com.irsyaad.dicodingsubmission.seport.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailLeague
import com.irsyaad.dicodingsubmission.seport.repository.DetailLeagueCallback
import com.irsyaad.dicodingsubmission.seport.repository.Repository

class DetaiLeagueViewModel : ViewModel() {
    private val repository: Repository = Repository()

    private val detailLeague: MutableLiveData<ListDetailLeague> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()


    fun getDetailLeague(id: Int): LiveData<ListDetailLeague> {
        if(detailLeague.value == null) setDataDetailLeague(id)
        return detailLeague
    }

    private fun setDataDetailLeague(id: Int){
        showLoading.value = true

        repository.getDetailLeagueRepository(id, object : DetailLeagueCallback {
            override fun onSuccess(response: ListDetailLeague) {
                detailLeague.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }
}