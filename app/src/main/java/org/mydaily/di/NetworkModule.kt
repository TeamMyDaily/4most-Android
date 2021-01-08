package org.mydaily.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.mydaily.data.remote.api.*
import org.mydaily.network.AuthInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
    single<Retrofit>{
        Retrofit.Builder()
            .client(get())
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
}