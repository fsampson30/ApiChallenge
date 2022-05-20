package com.sampson.apichallenge.controller

import com.sampson.apichallenge.model.CheckIn
import com.sampson.apichallenge.model.Events
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("events")
    fun getAllEvents() : Observable<List<Events>>

    @POST("checkin")
    fun postCheckIn(@Body checkIn: CheckIn) : Observable<CheckIn>
}