package com.sampson.apichallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CheckIn(
    val eventId: Int = 0,
    val name: String = "",
    val email: String = ""
): Parcelable
