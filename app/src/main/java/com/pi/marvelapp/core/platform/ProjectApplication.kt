package com.pi.marvelapp.core.platform

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.pi.marvelapp.BuildConfig
import com.pi.marvelapp.core.network.NetworkConnectivityObserver
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ProjectApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        injectMultiDex()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun injectMultiDex() {
        MultiDex.install(this.applicationContext)
    }

    companion object {
        lateinit var appContext: Context

        /**
         * Used for checking internet connectivity statuses
         * Unavailable, Available, Lost and Losing states
         */
        val connectivityObserver: NetworkConnectivityObserver by lazy {
            NetworkConnectivityObserver(appContext)
        }
    }
}
