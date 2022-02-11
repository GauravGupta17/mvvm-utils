package com.gaurav.dask

import android.app.Application
import com.gaurav.dask.core.di.databaseModule
import com.gaurav.dask.core.di.repoModule
import com.gaurav.dask.core.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupTimber()
    }
    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(
                Timber.DebugTree()
            )
        }
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(databaseModule, repoModule, vmModule)
            )
        }

    }
}