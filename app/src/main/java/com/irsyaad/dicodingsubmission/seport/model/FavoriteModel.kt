package com.irsyaad.dicodingsubmission.seport.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteModel(
    @ColumnInfo(name = "id_data") val idData : Int,
    val idHomeTeam: Int,
    val homeTeam: String,
    val homeTeamScore: String?,
    val idAwayTeam: Int,
    val awayTeam: String,
    val awayTeamScore: String?,
    var name: String?,
    var date: String?,
    var time: String?,
    var thumbnails: String?,
    val type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)