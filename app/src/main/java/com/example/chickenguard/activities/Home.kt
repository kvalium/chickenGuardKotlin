package com.example.chickenguard.activities

import android.content.Context
import android.os.Bundle
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSunsetHour()
        handleWarnBeforeTimeSeek()
    }

    private fun handleWarnBeforeTimeSeek() {
        val warnBeforeTimeSeek = requireView().findViewById<SeekBar>(R.id.warnBeforeTime)
        // init seek and time from stored value
        warnBeforeTimeSeek.progress = getStoredTime()
        setWarnBeforeMinutes(getStoredTime())

        warnBeforeTimeSeek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                setWarnBeforeMinutes(warnBeforeTimeSeek.progress)
            }

            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {

                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
                with(sharedPref.edit()) {
                    putInt(getString(R.string.saved_warn_before_time), warnBeforeTimeSeek.progress)
                    apply()
                }

                // sendNotification()
            }
        })
    }

    private fun getStoredTime(): Int {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        return sharedPref!!.getInt(getString(R.string.saved_warn_before_time), 0)
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(
            requireContext(),
            resources.getString(R.string.notification_channel_id)
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(resources.getString(R.string.notification_title))
            .setContentText(resources.getString(R.string.notification_content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(1, builder.build())
        }
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