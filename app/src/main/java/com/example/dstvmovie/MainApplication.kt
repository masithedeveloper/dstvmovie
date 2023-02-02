package com.example.dstvmovie

import android.app.Application
import androidx.multidex.MultiDex
import com.example.dstvmovie.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        if (isDevMode()) {
            // init timber
            Timber.plant(Timber.DebugTree())
        } else {
        }
    }
}

fun isDevMode() = BuildConfig.BUILD_TYPE != "release"
