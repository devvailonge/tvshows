package com.devvailonge.home.domain

import com.devvailonge.seriesService.SeriesDto
import com.devvailonge.seriesService.SeriesImageDto
import com.devvailonge.seriesService.SeriesRatingDto

data class Series(
    val id: Int,
    val image: SeriesImage,
    val language: String,
    val name: String,
    val genres: List<String>,
    val summary: String,
    val rating: SeriesRating
)

data class SeriesRating(val average: Double)

data class SeriesImage(val original: String)

fun List<SeriesDto>.asDomain() = map {
    Series(
        id = it.id,
        image = it.image.asDomain(),
        language = it.language,
        name = it.name,
        genres = it.genres,
        summary = it.summary,
        rating = it.rating.asDomain()
    )
}

fun SeriesImageDto.asDomain() = SeriesImage(original = this.original)
fun SeriesRatingDto.asDomain() = SeriesRating(average = this.average)
