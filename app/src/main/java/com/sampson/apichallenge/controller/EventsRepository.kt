package com.sampson.apichallenge.controller

class EventsRepository (private val apiService: ApiService) {

    fun getAllEvents() = apiService.getAllEvents()

}
