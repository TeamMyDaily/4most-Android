package org.mydaily

import android.app.Application
import org.mydaily.data.local.FourMostPreference
import org.mydaily.di.networkModule
import org.mydaily.di.remoteDataSourceModule
import org.mydaily.di.repositoryModule
import org.mydaily.di.viewModelModule
import org.mydaily.util.extension.setUpKoin

class MyDailyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin(
            this,
            networkModule,
            remoteDataSourceModule,
            repositoryModule,
            viewModelModule
        )
        FourMostPreference.init(applicationContext)
    }
}