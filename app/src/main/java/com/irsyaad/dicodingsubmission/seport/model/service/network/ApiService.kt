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
    fun searchEvent(@Query("e") text: String): Call<SearchEventLeague>

    @GET("lookupteam.php")
    fun detailTeam(@Query("id") id: Int): Call<DetailTeamLeague>
}