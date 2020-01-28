package com.irsyaad.dicodingsubmission.seport.model.response

data class StandingsEventResponse(
    val table: List<StandingTable>
)

data class StandingTable(
    val draw: Int,
    val goalsagainst: Int,
    val goalsdifference: Int,
    val goalsfor: Int,
    val loss: Int,
    val name: String,
    val played: Int,
    val teamid: String,
    val total: Int,
    val win: Int
)