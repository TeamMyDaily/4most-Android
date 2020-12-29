package org.mydaily

import android.app.Application
import org.mydaily.di.viewModelModule
import org.mydaily.extension.setUpKoin

class MyDailyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin(
            this,
            viewModelModule
        )
    }
}