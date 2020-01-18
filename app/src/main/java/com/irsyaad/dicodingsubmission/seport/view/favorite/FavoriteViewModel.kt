package com.irsyaad.dicodingsubmission.seport.view.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.irsyaad.dicodingsubmission.seport.model.FavoriteModel
import com.irsyaad.dicodingsubmission.seport.model.service.local.room.FavoriteDatabase
import com.irsyaad.dicodingsubmission.seport.repository.FavoriteRepository
import kotlinx.coroutines.launch


class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteDao = FavoriteDatabase.getInstance(application)!!.favoriteDao()
    private val repository: FavoriteRepository = FavoriteRepository(favoriteDao)

    var isFavorite: MutableLiveData<Boolean> = MutableLiveData()

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
}