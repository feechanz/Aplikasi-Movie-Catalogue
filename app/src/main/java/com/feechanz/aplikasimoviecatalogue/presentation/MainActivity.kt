package com.feechanz.aplikasimoviecatalogue.presentation

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.feechanz.aplikasimoviecatalogue.R
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.navigationHostFragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_movie, R.id.navigation_show, R.id.navigation_favorite
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        mainBottomNavigation.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_settings) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
