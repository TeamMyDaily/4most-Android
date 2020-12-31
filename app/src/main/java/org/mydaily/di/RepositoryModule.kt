package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.repository.SampleRepo
import org.mydaily.data.repository.SampleRepoImpl

val repositoryModule = module {
    single<SampleRepo> { SampleRepoImpl(get()) }
}