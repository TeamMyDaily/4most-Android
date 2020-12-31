package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.remote.api.SampleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<SampleService> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("BASE_URL")
            .build()
            .create(SampleService::class.java)
    }
}