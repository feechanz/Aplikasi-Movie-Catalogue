package com.feechanz.aplikasimoviecatalogue

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Feechan on 11/10/2019.
 */
class AplikasiMovieCatalogueApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .build()

        Realm.setDefaultConfiguration(realmConfiguration)
    }
}