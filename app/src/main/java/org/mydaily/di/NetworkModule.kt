package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.remote.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit>{
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("BASE_URL")
            .build()
    }
    single<GoalService> {
        get<Retrofit>().create(GoalService::class.java)
    }
    single<KeywordService> {
        get<Retrofit>().create(KeywordService::class.java)
    }
    single<ReportService> {
        get<Retrofit>().create(ReportService::class.java)
    }
    single<ReviewService> {
        get<Retrofit>().create(ReviewService::class.java)
    }
    single<TaskService> {
        get<Retrofit>().create(TaskService::class.java)
    }
    single<UserService> {
        get<Retrofit>().create(UserService::class.java)
    }
    single<SampleService> {
        get<Retrofit>().create(SampleService::class.java)
    }
}