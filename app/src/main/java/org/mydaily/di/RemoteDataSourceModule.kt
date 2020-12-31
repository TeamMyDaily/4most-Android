package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.remote.datasource.SampleRemoteDataSource
import org.mydaily.data.remote.datasource.SampleRemoteDataSourceImpl
import org.mydaily.data.repository.SampleRepo
import org.mydaily.data.repository.SampleRepoImpl

val remoteDataSourceModule = module {
    single<SampleRemoteDataSource> { SampleRemoteDataSourceImpl(get()) }
}