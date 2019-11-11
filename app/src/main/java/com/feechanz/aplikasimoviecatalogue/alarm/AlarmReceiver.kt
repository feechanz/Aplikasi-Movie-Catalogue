package com.feechanz.aplikasimoviecatalogue.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApi
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApiImpl
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        const val TYPE_DAILY_REMINDER = "DailyReminderAlarm"
        const val TYPE_RELEASE_REMINDER = "ReleaseReminderAlarm"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"

        const val ID_DAILY_REMINDER = 100
        const val ID_RELEASE_REMINDER = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val title = if (type.equals(TYPE_DAILY_REMINDER, ignoreCase = true)) {
            context.getString(
                R.string.daily_reminder
            )
        }else{
            context.getString(
                R.string.release_reminder
            )
        }
        val notifId = if (type.equals(TYPE_DAILY_REMINDER, ignoreCase = true)) ID_DAILY_REMINDER else ID_RELEASE_REMINDER
        if(notifId == ID_DAILY_REMINDER) {
            showAlarmNotification(context, title, message, notifId)
        }else{
            showReleaseTodayReminder(context, title, notifId)
        }
    }

    private fun showReleaseTodayReminder(context: Context, title:String,
                                         notifId:Int){
        val restApi:RestApi = RestApiImpl()
        var message = ""
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                val date = Date()
                val todayDate = simpleDateFormat.format(date)
                val movieResponses = restApi.getMoviesReleaseToday(
                    Constant.getEndpointPath(Constant.GET_MOVIES),
                    Constant.API_KEY,
                    todayDate,
                    todayDate
                )
                if (movieResponses.isSuccessful) {
                    message = context.getString(R.string.release_today) +": "
                    movieResponses.body().results?.map { m ->
                        val mov = MovieShow.getInstance(m)
                        message += mov.title + ", "
                    }
                    if(movieResponses.body().results == null ||
                        movieResponses.body().results!!.isEmpty()){
                        message = context.getString(R.string.no_release_today)
                    }
                }else{
                    message = context.getString(R.string.release_fail_with_code) + movieResponses
                        .code()
                }
            } catch (ex: Exception) {
                message = context.getString(R.string.release_error)+ex.message
            }

            showAlarmNotification(context, title, message, notifId)
        }

    }

    private fun showAlarmNotification(context: Context, title:String, message:String, notifId: Int){
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"
        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_movie_black)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }
}
