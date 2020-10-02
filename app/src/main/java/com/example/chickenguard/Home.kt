package com.example.chickenguard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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


        // Capture the layout's TextView and set the string as its text
        val textView = view.findViewById<TextView>(R.id.sunsetHour).apply {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val formatted = current.format(formatter)
            text = formatted
        }
    }
}