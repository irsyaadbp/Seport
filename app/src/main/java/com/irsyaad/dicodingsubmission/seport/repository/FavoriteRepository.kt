package com.irsyaad.dicodingsubmission.seport.repository

import androidx.lifecycle.LiveData
import com.irsyaad.dicodingsubmission.seport.model.FavoriteModel
import com.irsyaad.dicodingsubmission.seport.model.FavoriteTeamModel
import com.irsyaad.dicodingsubmission.seport.model.service.local.room.FavoriteDao

class FavoriteRepository(private var favDao: FavoriteDao) {

    val nextEventFavorite: LiveData<List<FavoriteModel>> = favDao.getFavorite("next")
    val pastEventFavorite: LiveData<List<FavoriteModel>> = favDao.getFavorite("past")

    suspend fun checkFavorite(id: Int, type: String): Int {
        return favDao.checkFavoriteById(id, type)
    }

    suspend fun insert(fav: FavoriteModel){
        favDao.insert(fav)
    }

    suspend fun delete(id: Int, type: String){
        favDao.deleteFavorite(id, type)
    }

    val teamFavorite: LiveData<List<FavoriteTeamModel>> = favDao.getFavoriteTeam()

    suspend fun checkFavoriteTeam(id: Int): Int {
        return favDao.checkFavoriteTeamById(id)
    }

    suspend fun insertTeam(fav: FavoriteTeamModel){
        favDao.insertTeam(fav)
    }

    suspend fun deleteTeam(id: Int){
        favDao.deleteFavoriteTeam(id)
    }
}