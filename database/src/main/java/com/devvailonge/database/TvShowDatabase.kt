package com.devvailonge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SeriesCacheDto::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TvShowDatabase : RoomDatabase() {

    abstract fun seriesDao(): SeriesDao

    companion object {
        const val DATABASE_NAME: String = "tvShow_db"
    }
}