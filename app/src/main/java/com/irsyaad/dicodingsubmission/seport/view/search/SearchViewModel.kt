package com.irsyaad.dicodingsubmission.seport.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.repository.EventCallback
import com.irsyaad.dicodingsubmission.seport.repository.Repository

class SearchViewModel : ViewModel(){
    private val repository: Repository = Repository()
    private val listSearchEvent: MutableLiveData<List<EventModel>> = MutableLiveData()

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
}