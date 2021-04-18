package com.devvailonge.home.di

import com.devvailonge.home.domain.FetchSeriesUseCaseImpl
import com.devvailonge.home.presentation.FetchSeriesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeViewModelModule {

    @ViewModelScoped
    @Binds
    abstract fun bindFetchSeriesUseCase(impl: FetchSeriesUseCaseImpl): FetchSeriesUseCase
}