package com.sampson.apichallenge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events(
    val date: String = "",
    val description: String = "",
    val image: String = "",
    val longitude: Double = 0.0,
    val latitude: Double = 0.0,
    val price: Double = 0.0,
    val title: String = "",
    val id: Int = 0
) : Parcelable


