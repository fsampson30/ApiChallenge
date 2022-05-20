package com.sampson.apichallenge

import com.google.common.truth.Truth
import org.junit.Test
import java.lang.NumberFormatException


class DateUtilsTest {

    private val date = DateUtils

    @Test
    fun valueIsCorrect() {
        val value = date.convertMiliToDate("265498654")
        Truth.assertThat(value).contains(String())
    }

    @Test
    fun valueToBig(){
        val value = date.convertMiliToDate("26549869999999999999999999999999999999")
        Truth.assertThat(value.length).isGreaterThan(10)
    }

    @Test(expected = NumberFormatException::class)
    fun valueIsEmpty(){
        date.convertMiliToDate("")
    }
}