package com.irsyaad.dicodingsubmission.seport.model.service.network

import com.irsyaad.dicodingsubmission.seport.BuildConfig.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRepository {
    fun getData(): ApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API_URL)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}