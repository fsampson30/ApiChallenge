package com.sampson.apichallenge.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sampson.apichallenge.R

class EventCheckInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_check_in)

        val txtPersonName: TextView = findViewById(R.id.txtCheckInActivityName)
        val txtPersonEmail: TextView = findViewById(R.id.txtCheckInActivityEmail)
        val btnConfirm: Button = findViewById(R.id.btnCheckInActivityConfirm)

        btnConfirm.setOnClickListener {
            if (txtPersonName.text.isEmpty() or txtPersonEmail.text.isEmpty()){
                txtPersonName.error = getString(R.string.error_message)
                txtPersonEmail.error = getString(R.string.error_message)
            } else {
                val replyIntent = Intent()
                replyIntent.apply {
                    putExtra("person_name",txtPersonName.text)
                    putExtra("person_email", txtPersonEmail.text)
                }
                setResult(Activity.RESULT_OK,replyIntent)
                finish()
            }
        }
    }
}