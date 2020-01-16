package com.irsyaad.dicodingsubmission.seport.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irsyaad.dicodingsubmission.seport.model.DetailEventModel
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.FavoriteModel
import com.irsyaad.dicodingsubmission.seport.model.SportModel
import com.irsyaad.dicodingsubmission.seport.model.response.*
import com.irsyaad.dicodingsubmission.seport.model.service.FavoriteRepository
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport
import com.irsyaad.dicodingsubmission.seport.model.service.local.room.FavoriteDatabase
import com.irsyaad.dicodingsubmission.seport.model.service.network.ApiRepository
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val service = ApiRepository.getData()
    private val favoriteDao = FavoriteDatabase.getInstance(application)!!.favoriteDao()
    private val repository: FavoriteRepository = FavoriteRepository(favoriteDao)

    private val listLeague: MutableLiveData<ArrayList<SportModel>> = MutableLiveData()
    private val detailLeague: MutableLiveData<ListDetailLeague> = MutableLiveData()
    private val pastEvent: MutableLiveData<List<EventModel>> = MutableLiveData()
    private val nextEvent: MutableLiveData<List<EventModel>> = MutableLiveData()
    private val detailEventLeague: MutableLiveData<ArrayList<DetailEventModel>> = MutableLiveData()
    private val detailHomeTeam: MutableLiveData<ListDetailTeam> = MutableLiveData()
    private val detailAwayTeam: MutableLiveData<ListDetailTeam> = MutableLiveData()
    private val listSearchMatch: MutableLiveData<List<EventModel>> = MutableLiveData()

    var isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    var showLoading: MutableLiveData<Boolean> = MutableLiveData()
    var isError: MutableLiveData<Boolean> = MutableLiveData()

    val getNextEventFavorite: LiveData<List<FavoriteModel>> = repository.nextEventFavorite
    val getPastEventFavorite: LiveData<List<FavoriteModel>> = repository.pastEventFavorite

    fun checkFavorite(id:Int, type: String)= viewModelScope.launch {
        isFavorite.value = repository.checkFavorite(id, type) > 0
    }

    fun insertFavorite(favorite: FavoriteModel) = viewModelScope.launch {
        repository.insert(favorite)
    }

    fun deleteFavorite(id: Int, type: String) = viewModelScope.launch {
        repository.delete(id, type)
    }

    fun getListLeague(): LiveData<ArrayList<SportModel>> {
        if(listLeague.value == null) listLeague.postValue(DataSport.listDataSport)
        return listLeague
    }

    fun getDetailLeague(id: Int): LiveData<ListDetailLeague>{
        if(detailLeague.value == null) setDataDetailLeague(id)
        return detailLeague
    }

    val detailLeagues: LiveData<ListDetailLeague>
        get() = detailLeague

    fun setDataDetailLeague(id: Int){
        showLoading.value = true

        service.detailLeague(id).enqueue(object : Callback<DetailLeagueModel>{
            override fun onFailure(call: Call<DetailLeagueModel>, t: Throwable) {
                showLoading.value = false
                isError.value = true
            }

            override fun onResponse(
                call: Call<DetailLeagueModel>,
                response: Response<DetailLeagueModel>
            ) {
                showLoading.value = false
                isError.value = false

                val data = response.body()

                detailLeague.value = data?.leagues?.get(0)
            }

        })
    }

    fun getPastEvent(id: Int): LiveData<List<EventModel>>{
        if(pastEvent.value == null) setDataPastEvent(id)
        return pastEvent
    }

    private fun setDataPastEvent(id: Int){
        showLoading.value = true

        service.pastEvent(id).enqueue(object : Callback<PastEventLeague>{
            override fun onFailure(call: Call<PastEventLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
            }

            override fun onResponse(
                call: Call<PastEventLeague>,
                response: Response<PastEventLeague>
            ) {
                showLoading.value = false
                isError.value = false

                val data = response.body()?.events?.map {
                    with(it) {
                        EventModel(
                            idEvent,
                            idHomeTeam,
                            idAwayTeam,
                            strEvent,
                            dateEvent,
                            strTime,
                            strHomeTeam,
                            strAwayTeam,
                            intHomeScore,
                            intAwayScore,
                            strThumb
                        )
                    }
                }

                pastEvent.value = data
            }

        })
    }

    fun getNextEvent(id: Int): LiveData<List<EventModel>>{
        if(nextEvent.value == null) setDataNextEvent(id)
        return nextEvent
    }

    private fun setDataNextEvent(id: Int){
        showLoading.value = true

        service.nextEvent(id).enqueue(object : Callback<NextEventLeague>{
            override fun onFailure(call: Call<NextEventLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
            }

            override fun onResponse(
                call: Call<NextEventLeague>,
                response: Response<NextEventLeague>
            ) {
                showLoading.value = false
                isError.value = false

                val data = response.body()?.events?.map {
                    with(it) {
                        EventModel(
                            idEvent,
                            idHomeTeam,
                            idAwayTeam,
                            strEvent,
                            dateEvent,
                            strTime,
                            strHomeTeam,
                            strAwayTeam,
                            intHomeScore,
                            intAwayScore,
                            strThumb
                        )
                    }
                }

                nextEvent.value = data
            }

        })
    }

    fun getDetailEventLeague(id: Int): LiveData<ArrayList<DetailEventModel>>{
        if(detailEventLeague.value == null) setDataDetailEventLeague(id)
        return detailEventLeague
    }

    private fun setDataDetailEventLeague(idEvent: Int){
        showLoading.value = true

        service.detailEvent(idEvent).enqueue(object : Callback<DetailEventLeague>{
            override fun onFailure(call: Call<DetailEventLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
            }

            override fun onResponse(
                call: Call<DetailEventLeague>,
                response: Response<DetailEventLeague>
            ) {
                showLoading.value = false
                isError.value = false

                val titles = DataSport.dataDetailEvent
                val data = response.body()?.events?.get(0)
                val detail = arrayListOf<DetailEventModel>()
                
                for (i in titles.indices){
                    if (data != null) {
                        detail.add(
                            when(i){
                                0 -> DetailEventModel(data.intHomeScore, titles[i], data.intAwayScore)
                                1 -> DetailEventModel(data.intHomeShots, titles[i], data.intAwayShots)
                                2 -> DetailEventModel(data.strAwayRedCards, titles[i], data.strAwayRedCards)
                                3 -> DetailEventModel(data.strHomeYellowCards, titles[i], data.strAwayYellowCards)
                                4 -> DetailEventModel(data.strHomeFormation, titles[i], data.strAwayFormation)
                                5 -> DetailEventModel(data.strHomeGoalDetails, titles[i], data.strAwayGoalDetails)
                                6 -> DetailEventModel(
                                    data.strHomeLineupDefense,
                                    titles[i],
                                    data.strAwayLineupDefense
                                )
                                7 -> DetailEventModel(
                                    data.strHomeLineupForward,
                                    titles[i],
                                    data.strAwayLineupForward
                                )
                                8 -> DetailEventModel(
                                    data.strHomeLineupGoalkeeper,
                                    titles[i],
                                    data.strAwayLineupGoalkeeper
                                )
                                9 -> DetailEventModel(
                                    data.strHomeLineupMidfield,
                                    titles[i],
                                    data.strAwayLineupMidfield
                                )
                                10 -> DetailEventModel(
                                    data.strHomeLineupSubstitutes,
                                    titles[i],
                                    data.strAwayLineupSubstitutes
                                )
                                else -> DetailEventModel("-", "-", "-")
                            }
                        )
                    }
                }

                detailEventLeague.value = detail
            }

        })
    }

    fun getHomeTeam(idTeam: Int):LiveData<ListDetailTeam>{
        if(detailHomeTeam.value == null) setDetailHomeTeam(idTeam)
        return detailHomeTeam
    }

    private fun setDetailHomeTeam(idTeam: Int){
        showLoading.value = true

        service.detailTeam(idTeam).enqueue(object : Callback<DetailTeamLeague>{
            override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
                t.printStackTrace()
            }

            override fun onResponse(call: Call<DetailTeamLeague>, response: Response<DetailTeamLeague>) {
                showLoading.value = false
                isError.value = false
                val data = response.body()?.teams?.get(0)

                detailHomeTeam.value = data
            }

        })
    }

    fun getAwayTeam(idTeam: Int):LiveData<ListDetailTeam>{
        if(detailAwayTeam.value == null) setDetailAwayTeam(idTeam)
        return detailAwayTeam
    }

    private fun setDetailAwayTeam(idTeam: Int){
        showLoading.value = true

        service.detailTeam(idTeam).enqueue(object : Callback<DetailTeamLeague>{
            override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
                t.printStackTrace()
            }

            override fun onResponse(call: Call<DetailTeamLeague>, response: Response<DetailTeamLeague>) {
                showLoading.value = false
                isError.value = false
                val data = response.body()?.teams?.get(0)

                detailAwayTeam.value = data
            }

        })
    }

    fun getSearchEvent(): LiveData<List<EventModel>>{
        return listSearchMatch
    }

    fun setDataSearch(query: String){
        showLoading.value = true
        service.searchEvent(query).enqueue(object: Callback<SearchEventLeague>{
            override fun onFailure(call: Call<SearchEventLeague>, t: Throwable) {
                showLoading.value = false
                isError.value = true
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<SearchEventLeague>,
                response: Response<SearchEventLeague>
            ) {
                showLoading.value = false
                isError.value = false
                val data = response.body()?.event
                    ?.filter { it -> it.strSport == "Soccer" }
                    ?.map {
                        with(it) {
                            EventModel(
                                idEvent,
                                idHomeTeam,
                                idAwayTeam,
                                strEvent,
                                dateEvent,
                                strTime,
                                strHomeTeam,
                                strAwayTeam,
                                intHomeScore,
                                intAwayScore,
                                strThumb
                            )
                        }
                    }

                listSearchMatch.value = data
            }

        })
    }
}