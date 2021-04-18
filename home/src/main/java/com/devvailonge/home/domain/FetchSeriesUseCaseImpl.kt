package com.devvailonge.home.domain

import com.devvailonge.home.domain.HomeSyncState.SeriesFailure
import com.devvailonge.home.domain.HomeSyncState.SeriesSuccess
import com.devvailonge.home.presentation.FetchSeriesUseCase
import com.devvailonge.seriesService.FetchRemoteSeries
import com.devvailonge.seriesService.Resource.Status.ERROR
import com.devvailonge.seriesService.Resource.Status.SUCCESS
import com.devvailonge.seriesService.SeriesDto
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
                when (response.status) {
                    SUCCESS -> handleSuccessStatus(response.data)
                    ERROR -> HomeState(reason = response.message, syncState = SeriesFailure)
                }
            }
            .onStart { emit(HomeState(syncState = HomeSyncState.SeriesLoading)) }
            .catch { emit(HomeState(reason = it, syncState = SeriesFailure)) }
            .onCompletion { emit(HomeState(syncState = HomeSyncState.SeriesIdle)) }
            .scan(HomeState(), { old, new -> old.reducer(new.series, new.reason, new.syncState) })

    private fun handleSuccessStatus(response: List<SeriesDto>?): HomeState =
        response?.takeIf { it.isNullOrEmpty().not() }
            ?.let { HomeState(series = it.asDomain(), syncState = SeriesSuccess) }
            ?: run { HomeState(syncState = HomeSyncState.EmptySeries) }
}
