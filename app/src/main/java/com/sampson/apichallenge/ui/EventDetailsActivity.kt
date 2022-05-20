package com.sampson.apichallenge.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sampson.apichallenge.R
import com.sampson.apichallenge.controller.EventsApplication
import com.sampson.apichallenge.model.CheckIn
import com.sampson.apichallenge.model.Events
import com.sampson.apichallenge.viewmodel.EventViewModel
import com.sampson.apichallenge.viewmodel.EventsViewModelFactory
import com.squareup.picasso.Picasso

class EventDetailsActivity : AppCompatActivity() {

    private val eventViewModel: EventViewModel by viewModels {
        EventsViewModelFactory((application as EventsApplication).eventsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        val imgEvent: ImageView = findViewById(R.id.imgDetailsActivity)
        val txtTitle: TextView = findViewById(R.id.txtEventDetailsTitle)
        val txtDescription: TextView = findViewById(R.id.txtEventDetailsDescription)
        val txtPrice: TextView = findViewById(R.id.txtEventDetailsPrice)
        val txtLocation: TextView = findViewById(R.id.txtEventDetailsLocation)
        val fabCheckEvent: FloatingActionButton = findViewById(R.id.fabCheckEvent)

        val event = intent.getParcelableExtra<Events>("event")

        txtTitle.text = event?.title
        txtDescription.text = event?.description
        txtPrice.text = getString(R.string.price) + "${event?.price.toString()}"

        Picasso.get().load(event?.image).fetch()
        Picasso.get().load(event?.image).placeholder(R.mipmap.ic_launcher).into(imgEvent)

        txtLocation.setOnClickListener {
            val intentUri = Uri.parse("geo:${event?.latitude},${event?.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, intentUri).apply {
                setPackage(getString(R.string.maps_package))
            }
            startActivity(mapIntent)
        }

        val register = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val eventId = event?.id ?: 0
                val personName = it.data?.getStringExtra("person_name") ?: ""
                val personEmail = it.data?.getStringExtra("person_email") ?: ""
                val checkIn = CheckIn(eventId,personName,personEmail)
                eventViewModel.postCheckIn(checkIn)
                Toast.makeText(this,getString(R.string.success_message),Toast.LENGTH_SHORT).show()
            }
        }

        fabCheckEvent.setOnClickListener {
            val intent = Intent(baseContext, EventCheckInActivity::class.java)
            register.launch(intent)
        }

    }
}