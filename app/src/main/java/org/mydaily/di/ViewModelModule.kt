package org.mydaily.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mydaily.ui.viewmodel.DailyViewModel
import org.mydaily.ui.viewmodel.MainViewModel
import org.mydaily.ui.viewmodel.MyPageViewModel
import org.mydaily.ui.viewmodel.RemindViewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { DailyViewModel() }
    viewModel { RemindViewModel() }
    viewModel { MyPageViewModel() }
}