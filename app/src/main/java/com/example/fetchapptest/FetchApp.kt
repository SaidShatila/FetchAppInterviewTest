package com.example.fetchapptest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FetchApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}