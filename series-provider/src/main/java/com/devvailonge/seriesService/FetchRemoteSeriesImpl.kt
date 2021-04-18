package com.devvailonge.seriesService

import com.devvailonge.database.SeriesDao
import com.devvailonge.utils.cacheStrategy
import com.devvailonge.utils.runCatchingResource
import dagger.Reusable
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@Reusable
class FetchRemoteSeriesImpl @Inject constructor(
    private val seriesApi: SeriesApi,
    private val seriesDao: SeriesDao
) : FetchRemoteSeries {

    override fun run(): Flow<Resource<List<SeriesDto>>> = flow {

        runCatchingResource {
            cacheStrategy(
                databaseQuery = { seriesDao.get().asNetworkDomain() },
                networkCall = { seriesApi.fetchSeries() },
                saveCallResult = { seriesDao.insert(* it.asCacheDomain()) }
            )
        }.run { emit(this) }
    }.flowOn(IO)
}