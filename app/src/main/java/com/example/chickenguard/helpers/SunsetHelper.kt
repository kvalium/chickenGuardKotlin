package com.example.chickenguard.helpers

import org.shredzone.commons.suncalc.SunTimes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SunsetHelper {
    companion object {
        fun getLocationSunsetHour(lat: Double, lng: Double): String {
            val sunset = SunTimes.compute().on(LocalDateTime.now()).at(lat, lng).execute()
            return sunset.set!!.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }
}