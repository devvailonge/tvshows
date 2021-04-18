package com.devvailonge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg series: SeriesCacheDto)

    @Query("SELECT * from series WHERE id = :id")
    suspend fun getTvSeries(id: Int): SeriesCacheDto

    @Query("SELECT * from series")
    suspend fun get(): List<SeriesCacheDto>

}