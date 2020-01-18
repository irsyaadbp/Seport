package com.irsyaad.dicodingsubmission.seport.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.DetailEventModel
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.repository.DetailEventCallback
import com.irsyaad.dicodingsubmission.seport.repository.DetailTeamCallback
import com.irsyaad.dicodingsubmission.seport.repository.EventCallback
import com.irsyaad.dicodingsubmission.seport.repository.Repository

class DetailEventViewModel : ViewModel(){
    private val repository: Repository = Repository()

    private val pastEvent: MutableLiveData<List<EventModel>> = MutableLiveData()
    private val nextEvent: MutableLiveData<List<EventModel>> = MutableLiveData()

    private val detailEventLeague: MutableLiveData<ArrayList<DetailEventModel>> = MutableLiveData()
    private val detailHomeTeam: MutableLiveData<ListDetailTeam> = MutableLiveData()
    private val detailAwayTeam: MutableLiveData<ListDetailTeam> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getDetailEventLeague(id: Int): LiveData<ArrayList<DetailEventModel>> {
        if(detailEventLeague.value == null) setDataDetailEventLeague(id)
        return detailEventLeague
    }

    private fun setDataDetailEventLeague(id: Int){
        showLoading.value = true

        repository.getDetailEventRepository(id, object : DetailEventCallback {
            override fun onSuccess(response: ArrayList<DetailEventModel>) {
                detailEventLeague.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getHomeTeam(idTeam: Int):LiveData<ListDetailTeam>{
        if(detailHomeTeam.value == null) setDetailHomeTeam(idTeam)
        return detailHomeTeam
    }

    private fun setDetailHomeTeam(id: Int){
        showLoading.value = true

        repository.getDetailTeamRepository(id, object : DetailTeamCallback{
            override fun onSuccess(response: ListDetailTeam) {
                detailHomeTeam.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getAwayTeam(idTeam: Int):LiveData<ListDetailTeam>{
        if(detailAwayTeam.value == null) setDetailAwayTeam(idTeam)
        return detailAwayTeam
    }

    private fun setDetailAwayTeam(id: Int){
        showLoading.value = true

        repository.getDetailTeamRepository(id, object : DetailTeamCallback{
            override fun onSuccess(response: ListDetailTeam) {
                detailAwayTeam.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getNextEvent(id: Int): LiveData<List<EventModel>>{
        if(nextEvent.value == null) setDataNextEvent(id)
        return nextEvent
    }

    private fun setDataNextEvent(id: Int){
        showLoading.value = true

        repository.getNextEventRepository(id, object: EventCallback{
            override fun onSuccess(response: List<EventModel>) {
                showLoading.value = false
                isError.value = false

                nextEvent.value = response
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getPastEvent(id: Int): LiveData<List<EventModel>>{
        if(pastEvent.value == null) setDataPastEvent(id)
        return pastEvent
    }

    private fun setDataPastEvent(id: Int){
        showLoading.value = true

        repository.getPastEventRepository(id, object: EventCallback{
            override fun onSuccess(response: List<EventModel>) {
                showLoading.value = false
                isError.value = false

                pastEvent.value = response
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }
}