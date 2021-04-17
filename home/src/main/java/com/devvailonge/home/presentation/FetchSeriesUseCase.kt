package com.devvailonge.home.presentation

import com.devvailonge.home.domain.HomeState
import kotlinx.coroutines.flow.Flow

interface FetchSeriesUseCase {

    fun perform(): Flow<HomeState>
}