package com.example.chickenguard.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.chickenguard.R
import org.shredzone.commons.suncalc.SunTimes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Chicken Guard Home
 */
class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().findViewById<TextView>(R.id.sunsetHour).apply {
            text = setSunset(46.2577139, -0.2102060)
        }
    }

    private fun setSunset(lat: Double, lng: Double): String {
        val sunset = SunTimes.compute().on(LocalDateTime.now()).at(lat, lng).execute()
        return sunset.set!!.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}