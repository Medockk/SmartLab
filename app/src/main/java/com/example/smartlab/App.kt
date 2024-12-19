package com.example.smartlab

import android.app.Application
import com.example.smartlab.di.moduleAuth
import com.example.smartlab.di.moduleCategory
import com.example.smartlab.di.moduleQueue
import com.example.smartlab.di.moduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(listOf(moduleAuth, moduleViewModel, moduleQueue,
                moduleCategory))
        }
    }
}