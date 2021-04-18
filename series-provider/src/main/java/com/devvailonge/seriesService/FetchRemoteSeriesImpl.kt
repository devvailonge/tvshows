package com.devvailonge.seriesService

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class FetchRemoteSeriesImpl @Inject constructor(
    private val seriesApi: SeriesApi
) : FetchRemoteSeries {

    override fun run(): Flow<List<SeriesDto>> = flow {
        emit(seriesApi.fetchSeries())
    }
}