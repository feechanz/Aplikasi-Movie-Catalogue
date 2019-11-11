package com.feechanz.aplikasimoviecatalogue.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feechanz.aplikasimoviecatalogue.R

/**
 * Created by Feechan on 11/11/2019.
 */

class ReminderPreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_preference)
        title = getString(R.string.change_reminder_setting)

        supportFragmentManager.beginTransaction()
            .add(R.id.setting_holder, ReminderPreferenceFragment()).commit()
    }

}