package com.stonetree.tablenow

import com.stonetree.tablenow.injectors.MainInjector
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            MainInjector().apply {
                loadKoinModules(startModules())
            }
        }
    }
}
