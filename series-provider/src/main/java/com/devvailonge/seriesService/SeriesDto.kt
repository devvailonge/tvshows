package com.devvailonge.seriesService

data class SeriesDto(
    val id: Int,
    val image: SeriesImageDto,
    val language: String,
    val name: String,
    val genres: List<String>,
    val summary: String,
    val rating: SeriesRatingDto
)

data class SeriesRatingDto(val average: Double)

data class SeriesImageDto(val original: String)
