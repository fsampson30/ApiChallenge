package com.sampson.apichallenge.ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.sampson.apichallenge.R
import com.sampson.apichallenge.adapter.EventsAdapter
import com.sampson.apichallenge.controller.EventsApplication
import com.sampson.apichallenge.model.Events
import com.sampson.apichallenge.viewmodel.EventViewModel
import com.sampson.apichallenge.viewmodel.EventsViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels {
        EventsViewModelFactory((application as EventsApplication).eventsRepository)
    }

    lateinit var dialog: Dialog
    lateinit var rvEvents: RecyclerView
    lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvEvents = findViewById(R.id.rvEventsMainActivity)

        eventsAdapter = EventsAdapter(baseContext, object : EventsAdapter.EventClickListener{
            override fun onEventClick(event: Events) {
                val intent = Intent(baseContext,EventDetailsActivity::class.java)
                intent.putExtra("event",event)
                startActivity(intent)
            }
        })

        rvEvents.adapter = eventsAdapter

        dialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog)
            setCancelable(false)
        }
        dialog.show()

        getEvents()
    }

    private fun getEvents() {
        eventViewModel.getAllEvents()
        eventViewModel.allEvents.observe(this){ events ->
            eventsAdapter.submitList(events)
            dialog.dismiss()
        }
    }
}