package com.irsyaad.dicodingsubmission.seport.model.service.network

import com.irsyaad.dicodingsubmission.seport.model.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("lookupleague.php")
    fun detailLeague(@Query("id") id: Int): Call<DetailLeagueModel>

    @GET("eventsnextleague.php")
    fun nextEvent(@Query("id") id: Int): Call<NextEventLeague>

    @GET("eventspastleague.php")
    fun pastEvent(@Query("id") id: Int): Call<PastEventLeague>

    @GET("lookupEvent.php")
    fun detailEvent(@Query("id") id: Int): Call<DetailEventLeague>

    @GET("searchevents.php")
    fun searchEvent(@Query("e") e: String): Call<SearchEventLeague>

    @GET("searchteams.php")
    fun searchTeam(@Query("t") t: String): Call<DetailTeamLeague>

    @GET("lookupteam.php")
    fun detailTeam(@Query("id") id: Int): Call<DetailTeamLeague>

    @GET("lookup_all_teams.php")
    fun allTeamLeague(@Query("id") id: Int): Call<DetailTeamLeague>

    @GET("lookuptable.php")
    fun getStandingTable(@Query("l") l: Int): Call<StandingsEventResponse>

    @GET("searchplayers.php")
    fun playerTeam(@Query("t") t: String): Call<PlayerResponse>

}