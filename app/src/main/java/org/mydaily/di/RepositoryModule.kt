package org.mydaily.di

import org.koin.dsl.module
import org.mydaily.data.repository.*

val repositoryModule = module {
    single<SampleRepo> { SampleRepoImpl(get()) }
    single<GoalRepo> { GoalRepoImpl(get()) }
    single<KeywordRepo> { KeywordRepoImpl(get()) }
    single<ReportRepo> { ReportRepoImpl(get()) }
    single<ReviewRepo> { ReviewRepoImpl(get()) }
    single<TaskRepo> { TaskRepoImpl(get()) }
    single<UserRepo> { UserRepoImpl(get()) }
}