package com.sampson.apichallenge.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.sampson.apichallenge.R
import com.sampson.apichallenge.databinding.ActivityEventCheckInBinding

class EventCheckInActivity : AppCompatActivity() {

    lateinit var binding : ActivityEventCheckInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventCheckInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCheckInActivityConfirm.setOnClickListener {
            if (binding.txtCheckInActivityName.text.isEmpty() or binding.txtCheckInActivityEmail.text.isEmpty()){
                binding.txtCheckInActivityName.error = getString(R.string.error_message)
                binding.txtCheckInActivityEmail.error = getString(R.string.error_message)
            } else {
                val replyIntent = Intent()
                replyIntent.apply {
                    putExtra("person_name",binding.txtCheckInActivityName.text)
                    putExtra("person_email", binding.txtCheckInActivityEmail.text)
                }
                setResult(Activity.RESULT_OK,replyIntent)
                finish()
            }
        }
    }
}