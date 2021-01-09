package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.remote.datasource.*

val remoteDataSourceModule = module {
    single<GoalRemoteDataSource> { GoalRemoteDataSourceImpl(get()) }
    single<KeywordRemoteDataSource> { KeywordRemoteDataSourceImpl(get()) }
    single<ReportRemoteDataSource> { ReportRemoteDataSourceImpl(get()) }
    single<ReviewRemoteDataSource> { ReviewRemoteDataSourceImpl(get()) }
    single<TaskRemoteDataSource> { TaskRemoteDataSourceImpl(get()) }
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }
}