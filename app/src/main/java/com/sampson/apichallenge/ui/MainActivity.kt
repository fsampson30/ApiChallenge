package com.sampson.apichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.sampson.apichallenge.R
import com.sampson.apichallenge.controller.EventsApplication
import com.sampson.apichallenge.viewmodel.EventViewModel
import com.sampson.apichallenge.viewmodel.EventsViewModelFactory

class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels {
        EventsViewModelFactory((application as EventsApplication).eventsRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getEvents()
    }

    private fun getEvents() {
        eventViewModel.getAllEvents()
        eventViewModel.allEvents.observe(this){ events ->
            Log.d("FLAVIO", events[0].description)
        }
    }
}