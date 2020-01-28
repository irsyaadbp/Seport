package com.irsyaad.dicodingsubmission.seport.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.repository.AllTeamLeagueCallback
import com.irsyaad.dicodingsubmission.seport.repository.EventCallback
import com.irsyaad.dicodingsubmission.seport.repository.Repository

class SearchViewModel : ViewModel(){
    private val repository: Repository = Repository()

    private val listSearchEvent: MutableLiveData<List<EventModel>> = MutableLiveData()
    private val listSearchTeam: MutableLiveData<ArrayList<ListDetailTeam>> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getSearchEvent(): LiveData<List<EventModel>> {
        return listSearchEvent
    }

    fun setDataSearch(text: String){
        showLoading.value = true
        repository.getSearchEventRepository(text, object: EventCallback{
            override fun onSuccess(response: List<EventModel>) {
                listSearchEvent.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value= true
            }

        })
    }

    val getSearchTeam : LiveData<ArrayList<ListDetailTeam>> = listSearchTeam

    fun setDataSearchTeam(text: String) {
        showLoading.value = true
        repository.getSearchTeamRepository(text, object: AllTeamLeagueCallback {
            override fun onSucces(response: ArrayList<ListDetailTeam>) {
                listSearchTeam.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value= true
            }

        })
    }
}