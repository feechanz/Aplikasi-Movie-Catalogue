package com.feechanz.aplikasimoviecatalogue.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.EXTRA_MESSAGE
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.EXTRA_TYPE
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.ID_DAILY_REMINDER
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.ID_RELEASE_REMINDER
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.TYPE_DAILY_REMINDER
import com.feechanz.aplikasimoviecatalogue.alarm.AlarmReceiver.Companion.TYPE_RELEASE_REMINDER
import java.util.*

/**
 * Created by Feechan on 11/11/2019.
 */
class ReminderPreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    private val DAILY_REMINDER_KEY = "daily_reminder_key"
    private val RELEASE_REMINDER_KEY = "release_reminder_key"
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key.equals(DAILY_REMINDER_KEY)) {
            val daily = sharedPreferences.getBoolean(DAILY_REMINDER_KEY, false)
            Log.d("chanz", "Daily reminder key = " + daily)
            if (daily) {
                setRepeatingAlarm(
                    context!!, TYPE_DAILY_REMINDER, getString(
                        R.string
                            .daily_reminder_message
                    ), 7, ID_DAILY_REMINDER, getString(
                        R.string
                            .daily_reminder
                    )
                )
            } else {
                cancelAlarm(context!!, getString(R.string.daily_reminder), ID_DAILY_REMINDER)
            }
        } else if (key.equals(RELEASE_REMINDER_KEY)) {
            val release = sharedPreferences.getBoolean(RELEASE_REMINDER_KEY, false)
            Log.d("release", "Release reminder key = " + release)
            if (release) {
                setRepeatingAlarm(
                    context!!, TYPE_RELEASE_REMINDER, getString(
                        R.string
                            .release_reminder
                    ), 8, ID_RELEASE_REMINDER, getString(
                        R.string
                            .release_reminder
                    )
                )
            } else {
                cancelAlarm(context!!, getString(R.string.release_reminder), ID_RELEASE_REMINDER)
            }
        }
    }

    fun setRepeatingAlarm(
        context: Context,
        type: String, message: String,
        hour: Int, idRepeating: Int, reminder: String
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, idRepeating, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, reminder + " set up!", Toast.LENGTH_SHORT).show()
    }

    fun cancelAlarm(context: Context, reminder: String, idRepeating: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, idRepeating, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        Toast.makeText(context, reminder + " dibatalkan!", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}