package com.devvailonge.seriesService

import kotlinx.coroutines.flow.Flow

interface FetchRemoteSeries {

    fun run() : Flow<List<SeriesDto>>
}