package com.sampson.apichallenge.controller

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class EventsApplication : Application(){

    lateinit var eventsRepository: EventsRepository

    private val baseUrl = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        eventsRepository = EventsRepository(apiService)
    }
}