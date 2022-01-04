package com.zhurov.android_table_of_multiplication.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.zhurov.android_table_of_multiplication.di.appContext

class App : Application() {

    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()
        appContext = applicationContext
    }
}