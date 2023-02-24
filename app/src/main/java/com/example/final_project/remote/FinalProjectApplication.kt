package com.example.final_project.remote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

class FinalProjectApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: FinalProjectApplication
            private set
    }
}