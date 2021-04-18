package com.devvailonge.home.di

import com.devvailonge.seriesService.FetchRemoteSeries
import com.devvailonge.seriesService.FetchRemoteSeriesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Singleton
    @Binds
    abstract fun bindFetchRemoteSeries(impl: FetchRemoteSeriesImpl): FetchRemoteSeries
}