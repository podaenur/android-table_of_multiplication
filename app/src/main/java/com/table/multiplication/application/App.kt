package com.table.multiplication.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.table.multiplication.di.appContext

class App : Application() {

    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
        appContext = applicationContext
    }
}