package com.sampson.apichallenge.controller

import com.sampson.apichallenge.model.CheckIn

class EventsRepository (private val apiService: ApiService) {

    fun getAllEvents() = apiService.getAllEvents()

    fun postCheckIn(checkIn: CheckIn) = apiService.postCheckIn(checkIn)

}
