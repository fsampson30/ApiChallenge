package com.sampson.apichallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.sampson.apichallenge.R
import com.sampson.apichallenge.model.Events
import com.squareup.picasso.Picasso

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        val imgEvent: ImageView = findViewById(R.id.imgDetailsActivity)
        val txtTitle: TextView = findViewById(R.id.txtEventDetailsTitle)
        val txtDescription: TextView = findViewById(R.id.txtEventDetailsDescription)

        val event = intent.getParcelableExtra<Events>("event")

        txtTitle.text = event?.title
        txtDescription.text = event?.description

        Picasso.get().load(event?.image).into(imgEvent)


    }
}