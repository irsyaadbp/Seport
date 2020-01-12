package com.irsyaad.dicodingsubmission.seport.model.service.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.irsyaad.dicodingsubmission.seport.model.FavoriteModel

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite WHERE type == :type")
    fun getFavorite(type: String): LiveData<List<FavoriteModel>>

    @Query("SELECT COUNT(*) FROM favorite WHERE id_data = :idData AND type = :type")
    suspend fun checkFavoriteById(idData: Int, type: String) : Int

    @Insert
    suspend fun insert(fav: FavoriteModel)

    @Query("DELETE FROM favorite WHERE id_data = :idData AND type = :type")
    suspend fun deleteFavorite(idData:Int, type: String)

}