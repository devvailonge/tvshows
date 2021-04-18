package com.devvailonge.seriesService

import com.devvailonge.database.SeriesCacheDto

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

fun List<SeriesDto>.asCacheDomain() = map {
    SeriesCacheDto(
        id = it.id,
        image = it.image.original,
        language = it.language,
        name = it.name,
        genres = it.genres,
        summary = it.summary,
        rating = it.rating.average
    )
}.toTypedArray()

fun List<SeriesCacheDto>.asNetworkDomain() = map {
    SeriesDto(
        id = it.id,
        image = SeriesImageDto(original = it.image),
        language = it.language,
        name = it.name,
        genres = it.genres,
        summary = it.summary,
        rating = SeriesRatingDto(average = it.rating)
    )
}

