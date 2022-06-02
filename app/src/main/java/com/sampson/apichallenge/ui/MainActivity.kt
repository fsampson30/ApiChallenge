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
import com.sampson.apichallenge.databinding.ActivityMainBinding
import com.sampson.apichallenge.model.Events
import com.sampson.apichallenge.viewmodel.EventViewModel
import com.sampson.apichallenge.viewmodel.EventsViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels {
        EventsViewModelFactory((application as EventsApplication).eventsRepository)
    }

    private lateinit var binding: ActivityMainBinding

    lateinit var dialog: Dialog
    lateinit var eventsAdapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        eventsAdapter = EventsAdapter(baseContext, object : EventsAdapter.EventClickListener{
            override fun onEventClick(event: Events) {
                val intent = Intent(baseContext,EventDetailsActivity::class.java)
                intent.putExtra("event",event)
                startActivity(intent)
            }
        })

        binding.rvEventsMainActivity.adapter = eventsAdapter

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

        eventViewModel.error.observe(this) { error ->
            dialog.dismiss()
            Toast.makeText(this, getString(R.string.download_error) + error.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}