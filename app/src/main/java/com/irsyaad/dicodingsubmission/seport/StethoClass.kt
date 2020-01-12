package com.irsyaad.dicodingsubmission.seport

import android.app.Application
import com.facebook.stetho.Stetho

class StethoClass : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}