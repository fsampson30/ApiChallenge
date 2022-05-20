package com.sampson.apichallenge.model

import com.google.common.truth.Truth

import org.junit.Test

class CheckInTest {

    @Test
    fun getEventId() {
        val checkIn = CheckIn(1,"Test","test@test.com")
        Truth.assertThat(checkIn.eventId).isGreaterThan(Int.MIN_VALUE)
        Truth.assertThat(checkIn.eventId).isLessThan(Int.MAX_VALUE)
        Truth.assertThat(checkIn.eventId).isNotNull()
    }

    @Test
    fun getName() {
        val checkIn = CheckIn(1,"Test","test@test.com")
        Truth.assertThat(checkIn.email).contains(String())
        Truth.assertThat(checkIn.email).isNotEmpty()
    }

    @Test
    fun getEmail() {
        val checkIn = CheckIn(1,"Test","test@test.com")
        Truth.assertThat(checkIn.email).contains(String())
        Truth.assertThat(checkIn.email).isNotEmpty()
    }

    @Test
    fun getNameIsEmpty(){
        val checkIn = CheckIn(1,"","")
        Truth.assertThat(checkIn.name).isEmpty()
    }

    @Test
    fun getEmailIsEmpty(){
        val checkIn = CheckIn(1,"","")
        Truth.assertThat(checkIn.email).isEmpty()
    }
}