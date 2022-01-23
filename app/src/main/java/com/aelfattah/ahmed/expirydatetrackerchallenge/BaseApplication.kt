package com.aelfattah.ahmed.expirydatetrackerchallenge

import android.app.Application
import com.aelfattah.ahmed.expirydatetrackerchallenge.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(allModules)
        }
    }
}