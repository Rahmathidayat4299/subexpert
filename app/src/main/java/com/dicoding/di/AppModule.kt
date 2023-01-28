package com.dicoding.di

import com.dicoding.core.domain.usecase.UseCaseUser
import com.dicoding.core.domain.usecase.UserInteractor
import com.dicoding.viewmodel.FavUserVm
import com.dicoding.viewmodel.FollowViewModel
import com.dicoding.viewmodel.ListUserVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rahmat Hidayat on 28/01/2023.
 */

val useCase = module {
    factory<UseCaseUser>{UserInteractor(get())}
}

val viewModel = module {
    viewModel { ListUserVm(get()) }
    viewModel { FavUserVm(get()) }
    viewModel { FollowViewModel(get()) }
}