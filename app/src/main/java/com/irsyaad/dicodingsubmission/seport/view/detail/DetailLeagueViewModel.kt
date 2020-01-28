package com.irsyaad.dicodingsubmission.seport.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailLeague
import com.irsyaad.dicodingsubmission.seport.model.response.ListDetailTeam
import com.irsyaad.dicodingsubmission.seport.model.response.ListPlayer
import com.irsyaad.dicodingsubmission.seport.model.response.StandingTable
import com.irsyaad.dicodingsubmission.seport.repository.*
import javax.security.auth.callback.Callback

class DetailLeagueViewModel : ViewModel() {
    private val repository: Repository = Repository()

    private val detailLeague: MutableLiveData<ListDetailLeague> = MutableLiveData()
    private val standingTable: MutableLiveData<List<StandingTable>> = MutableLiveData()
    private val allTeam: MutableLiveData<ArrayList<ListDetailTeam>> = MutableLiveData()
    private val detailTeam: MutableLiveData<ListDetailTeam> = MutableLiveData()
    private val allPlayer: MutableLiveData<ArrayList<ListPlayer>> = MutableLiveData()

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

    fun getStandingTable(id: Int): LiveData<List<StandingTable>>{
        if(standingTable.value == null) setDataStandingTable(id)
        return standingTable
    }

    private fun setDataStandingTable(id: Int){
        repository.getTableStandingRepository(id, object: StandingTableCallback {
            override fun onSuccess(response: List<StandingTable>) {
                standingTable.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getAllTeamLeague(id: Int): LiveData<ArrayList<ListDetailTeam>>{
        if(allTeam.value == null) setDataAllTeam(id)
        return allTeam
    }

    private fun setDataAllTeam(id: Int){
        repository.getAllTeamLeagueRepository(id, object: AllTeamLeagueCallback{
            override fun onSucces(response: ArrayList<ListDetailTeam>) {
                Log.d("result response", "" + response)
                allTeam.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getDetailTeam(id: Int):LiveData<ListDetailTeam> {
        if(detailTeam.value == null) setDataDetailTeam(id)
        return detailTeam
    }

    private fun setDataDetailTeam(id:Int){
        repository.getDetailTeamRepository(id, object : DetailTeamCallback{
            override fun onSuccess(response: ListDetailTeam) {
                Log.d("response", "" +response)
                detailTeam.value = response
                showLoading.value = false
                isError.value = false
            }

            override fun onError() {
                showLoading.value = false
                isError.value = true
            }

        })
    }

    fun getAllPlayer(club: String) : LiveData<ArrayList<ListPlayer>>{
        if(allPlayer.value == null) setDataAllPlayer(club)
        return allPlayer
    }

    private fun setDataAllPlayer(club: String){
        repository.getAllPlayerRepository(club, object: PlayerCallback{
            override fun onSucces(response: ArrayList<ListPlayer>) {
                allPlayer.value = response
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