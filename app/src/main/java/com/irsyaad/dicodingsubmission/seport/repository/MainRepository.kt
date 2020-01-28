package com.irsyaad.dicodingsubmission.seport.repository

import com.irsyaad.dicodingsubmission.seport.model.DetailEventModel
import com.irsyaad.dicodingsubmission.seport.model.EventModel
import com.irsyaad.dicodingsubmission.seport.model.response.*
import com.irsyaad.dicodingsubmission.seport.model.service.local.DataSport
import com.irsyaad.dicodingsubmission.seport.model.service.network.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val service = ApiRepository.getData()

    fun getDetailLeagueRepository(id: Int,callback: DetailLeagueCallback){
        service.detailLeague(id).enqueue(object: Callback<DetailLeagueModel>{
            override fun onFailure(call: Call<DetailLeagueModel>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<DetailLeagueModel>,
                response: Response<DetailLeagueModel>
            ) {
                val data = response.body()?.leagues

                data?.let {
                    callback.onSuccess(data[0])
                } ?:
                    callback.onError()

            }

        })
    }

    fun getDetailEventRepository(id: Int, callback: DetailEventCallback){
        service.detailEvent(id).enqueue(object : Callback<DetailEventLeague> {
            override fun onFailure(call: Call<DetailEventLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<DetailEventLeague>,
                response: Response<DetailEventLeague>
            ) {
                val data = response.body()?.events

                data?.let {
                    val titles = DataSport.dataDetailEvent
                    val detail = arrayListOf<DetailEventModel>()

                    for (i in titles.indices){

                            detail.add(
                                when(i){
                                    0 -> DetailEventModel(data[0].intHomeScore, titles[i], data[0].intAwayScore)
                                    1 -> DetailEventModel(data[0].intHomeShots, titles[i], data[0].intAwayShots)
                                    2 -> DetailEventModel(data[0].strAwayRedCards, titles[i], data[0].strAwayRedCards)
                                    3 -> DetailEventModel(data[0].strHomeYellowCards, titles[i], data[0].strAwayYellowCards)
                                    4 -> DetailEventModel(data[0].strHomeFormation, titles[i], data[0].strAwayFormation)
                                    5 -> DetailEventModel(data[0].strHomeGoalDetails, titles[i], data[0].strAwayGoalDetails)
                                    6 -> DetailEventModel(
                                        data[0].strHomeLineupDefense,
                                        titles[i],
                                        data[0].strAwayLineupDefense
                                    )
                                    7 -> DetailEventModel(
                                        data[0].strHomeLineupForward,
                                        titles[i],
                                        data[0].strAwayLineupForward
                                    )
                                    8 -> DetailEventModel(
                                        data[0].strHomeLineupGoalkeeper,
                                        titles[i],
                                        data[0].strAwayLineupGoalkeeper
                                    )
                                    9 -> DetailEventModel(
                                        data[0].strHomeLineupMidfield,
                                        titles[i],
                                        data[0].strAwayLineupMidfield
                                    )
                                    10 -> DetailEventModel(
                                        data[0].strHomeLineupSubstitutes,
                                        titles[i],
                                        data[0].strAwayLineupSubstitutes
                                    )
                                    else -> DetailEventModel("-", "-", "-")
                                }
                            )
                        }

                    callback.onSuccess(detail)
                } ?:
                callback.onError()

            }

        })
    }

    fun getDetailTeamRepository(id: Int, callback: DetailTeamCallback){
        service.detailTeam(id).enqueue(object : Callback<DetailTeamLeague> {
            override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<DetailTeamLeague>, response: Response<DetailTeamLeague>) {

                val data = response.body()?.teams

                data?.let {
                    callback.onSuccess(data[0])
                } ?: callback.onError()

            }

        })
    }

    fun getNextEventRepository(id: Int, callback: EventCallback){
        service.nextEvent(id).enqueue(object : Callback<NextEventLeague>{
            override fun onFailure(call: Call<NextEventLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<NextEventLeague>,
                response: Response<NextEventLeague>
            ) {
                val data = response.body()?.events

                data?.let {
                    val result = data.map {
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

                    callback.onSuccess(result)
                } ?: callback.onError()
            }

        })
    }

    fun getPastEventRepository(id: Int, callback: EventCallback){
        service.pastEvent(id).enqueue(object: Callback<PastEventLeague>{
            override fun onFailure(call: Call<PastEventLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<PastEventLeague>,
                response: Response<PastEventLeague>
            ) {
                val data = response.body()?.events

                data?.let {
                    val result = data.map {
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

                    callback.onSuccess(result)
                } ?: callback.onError()
            }

        })
    }

    fun getSearchEventRepository(text: String, callback: EventCallback){
        service.searchEvent(text).enqueue(object: Callback<SearchEventLeague>{
            override fun onFailure(call: Call<SearchEventLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<SearchEventLeague>,
                response: Response<SearchEventLeague>
            ) {
                val data = response.body()?.event
                data?.let {
                    val result = data.filter {  filter -> filter.strSport == "Soccer" }
                        .map {with ->
                            with(with) {
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

                    callback.onSuccess(result)
                } ?: callback.onError()
            }

        })
    }

    fun getTableStandingRepository(id: Int, callback: StandingTableCallback){
        service.getStandingTable(id).enqueue(object: Callback<StandingsEventResponse>{
            override fun onFailure(call: Call<StandingsEventResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<StandingsEventResponse>,
                response: Response<StandingsEventResponse>
            ) {
                val data = response.body()?.table
                data.let {
                    it?.let { result -> callback.onSuccess(result) }
                } ?: callback.onError()
            }
        })
    }

    fun getAllTeamLeagueRepository(id: Int, callback: AllTeamLeagueCallback){
        service.allTeamLeague(id).enqueue(object: Callback<DetailTeamLeague>{
            override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<DetailTeamLeague>,
                response: Response<DetailTeamLeague>
            ) {
                val data = response.body()?.teams

                data.let {
                    it?.let { result ->
                        callback.onSucces(result)
                    }
                } ?: callback.onError()
            }

        })
    }

    fun getAllPlayerRepository(club: String, callback: PlayerCallback){
        service.playerTeam(club).enqueue(object: Callback<PlayerResponse>{
            override fun onFailure(call: Call<PlayerResponse>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<PlayerResponse>,
                response: Response<PlayerResponse>
            ) {
                val data = response.body()?.player
                data?.let { callback.onSucces(it) } ?: callback.onError()
            }

        })
    }

    fun getSearchTeamRepository(text: String, callback: AllTeamLeagueCallback){
        service.searchTeam(text).enqueue(object: Callback<DetailTeamLeague> {
            override fun onFailure(call: Call<DetailTeamLeague>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(
                call: Call<DetailTeamLeague>,
                response: Response<DetailTeamLeague>
            ) {
                val data = response.body()?.teams
                data?.let { callback.onSucces(it) } ?: callback.onError()
            }

        })
    }

}

interface DetailLeagueCallback {
    fun onSuccess(response: ListDetailLeague)
    fun onError()
}

interface DetailEventCallback {
    fun onSuccess(response: ArrayList<DetailEventModel>)
    fun onError()
}

interface AllTeamLeagueCallback {
    fun onSucces(response: ArrayList<ListDetailTeam>)
    fun onError()
}

interface DetailTeamCallback {
    fun onSuccess(response: ListDetailTeam)
    fun onError()
}

interface EventCallback {
    fun onSuccess(response: List<EventModel>)
    fun onError()
}

interface StandingTableCallback {
    fun onSuccess(response: List<StandingTable>)
    fun onError()
}

interface PlayerCallback {
    fun onSucces(response: ArrayList<ListPlayer>)
    fun onError()
}