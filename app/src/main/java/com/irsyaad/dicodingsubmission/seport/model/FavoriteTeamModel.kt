package com.irsyaad.dicodingsubmission.seport.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_favorite")
data class FavoriteTeamModel(
    val idTeam: Int,
    val badge: String?,
    val clubName: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)