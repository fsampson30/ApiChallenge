package com.sampson.apichallenge

import java.math.BigInteger
import java.text.SimpleDateFormat

object DateUtils {

    fun convertMiliToDate(date: String): String{
        val simple = SimpleDateFormat("dd/MM/yyyy")
        val newDate = date.toBigInteger() / (1000*60*60).toBigInteger()
        return simple.format(newDate).toString()
    }
}