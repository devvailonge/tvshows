package com.devvailonge.seriesService

import retrofit2.http.GET

interface SeriesApi {

    @GET("/shows")
    suspend fun fetchSeries(): List<SeriesDto>
}