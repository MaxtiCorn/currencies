package com.maxticorn.currencies

import android.app.Application
import com.maxticorn.currencies.di.AppComponent
import com.maxticorn.currencies.di.AppModule
import com.maxticorn.currencies.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .build()
    }
}