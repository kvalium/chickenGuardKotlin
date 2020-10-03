package com.example.chickenguard.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.chickenguard.R
import com.example.chickenguard.helpers.SunsetHelper

/**
 * Chicken Guard Home
 */
class Home : Fragment() {

    private lateinit var notificationManager: NotificationManagerCompat
    val channelId = "Progress Notification"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationManager = NotificationManagerCompat.from(requireContext())

        return inflater.inflate(R.layout.home, container, false)
        //Create a Notification Manager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSunsetHour()
        handleWarnBeforeTimeSeek()


        //Creating a notification and setting its various attributes
        val notification =
            NotificationCompat.Builder(requireContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("GeeksforGeeks")
                .setContentText("Downloading")
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(1, notification.build())
    }

    private fun handleWarnBeforeTimeSeek() {
        val warnBeforeTimeSeek = requireView().findViewById<SeekBar>(R.id.warnBeforeTime)
        setWarnBeforeMinutes(warnBeforeTimeSeek.progress)
        warnBeforeTimeSeek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                setWarnBeforeMinutes(warnBeforeTimeSeek.progress)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {

                Log.d("PLOP", "poulet")


            }
        })
    }

    private fun setSunsetHour() {
        val sunsetLabel = requireView().findViewById<TextView>(R.id.sunsetHour)
        val sunsetHour = SunsetHelper.getLocationSunsetHour(46.2577139, -0.2102060)
        sunsetLabel.text = sunsetHour
    }

    private fun setWarnBeforeMinutes(minutes: Int) {
        val warnBeforeMinutes = requireView().findViewById<TextView>(R.id.warnBeforeMinutes)
        warnBeforeMinutes.text = (minutes * 5).toString()
    }
}