package com.sampson.apichallenge.model

import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.Test

class EventsTest {

    private val events = Events("321456789","Description","http://path.jpg",22.55,44.55,1.0,"title",0)

    @Test
    fun getDate() {
        Truth.assertThat(events.date).contains(String())
        Truth.assertThat(events.date).isNotEmpty()
    }

    @Test
    fun getDescription() {
        Truth.assertThat(events.description).contains(String())
        Truth.assertThat(events.description).isNotEmpty()
    }

    @Test
    fun getImage() {
        Truth.assertThat(events.image).contains(String())
        Truth.assertThat(events.image).isNotEmpty()
    }

    @Test
    fun getLongitude() {
        Truth.assertThat(events.longitude).isGreaterThan(Double.MIN_VALUE)
        Truth.assertThat(events.longitude).isLessThan(Double.MAX_VALUE)
        Truth.assertThat(events.price).isNotNull()
    }

    @Test
    fun getLatitude() {
        Truth.assertThat(events.latitude).isGreaterThan(Double.MIN_VALUE)
        Truth.assertThat(events.latitude).isLessThan(Double.MAX_VALUE)
        Truth.assertThat(events.price).isNotNull()
    }

    @Test
    fun getPrice() {
        Truth.assertThat(events.price).isGreaterThan(Double.MIN_VALUE)
        Truth.assertThat(events.price).isLessThan(Double.MAX_VALUE)
        Truth.assertThat(events.price).isNotNull()
    }

    @Test
    fun getTitle() {
        Truth.assertThat(events.title).contains(String())
        Truth.assertThat(events.title).isNotEmpty()
    }

    @Test
    fun getId() {
        Truth.assertThat(events.id).isGreaterThan(Int.MIN_VALUE)
        Truth.assertThat(events.id).isLessThan(Int.MAX_VALUE)
        Truth.assertThat(events.id).isNotNull()
    }

    @Test
    fun priceIsZero(){
        val events = Events()
        Truth.assertThat(events.price).isZero()
    }
}