package com.devvailonge.home.domain

import com.devvailonge.home.domain.HomeSyncState.SeriesSuccess
import com.devvailonge.home.presentation.FetchSeriesUseCase
import com.devvailonge.seriesService.FetchRemoteSeries
import dagger.Reusable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@Reusable
class FetchSeriesUseCaseImpl @Inject constructor(
    private val dataSource: FetchRemoteSeries
) : FetchSeriesUseCase {

    override fun perform(): Flow<HomeState> =
        dataSource.run()
            .map { response ->
                response.takeIf { it.isNotEmpty() }
                    ?.let { HomeState(series = it.asDomain(), syncState = SeriesSuccess) }
                    ?: run { HomeState(syncState = HomeSyncState.EmptySeries) }
            }
            .onStart { emit(HomeState(syncState = HomeSyncState.SeriesLoading)) }
            .catch { emit(HomeState(reason = it, syncState = HomeSyncState.SeriesFailure)) }
            .onCompletion { emit(HomeState(syncState = HomeSyncState.SeriesIdle)) }
            .scan(HomeState(), { old, new -> old.reducer(new.series, new.reason, new.syncState) })
}