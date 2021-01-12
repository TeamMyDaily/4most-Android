package org.mydaily.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mydaily.ui.viewmodel.*

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { DailyViewModel(get()) }
    viewModel { RemindViewModel() }
    viewModel { MyPageViewModel() }
    viewModel { ReportViewModel() }
    viewModel { UserViewModel(get()) }
    viewModel { GoalViewModel(get()) }
    viewModel { KeywordViewModel(get()) }
}