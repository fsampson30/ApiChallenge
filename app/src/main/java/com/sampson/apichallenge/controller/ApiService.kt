package com.sampson.apichallenge.controller

import com.sampson.apichallenge.model.Events
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/events")
    fun getAllEvents() : Observable<Events>
}