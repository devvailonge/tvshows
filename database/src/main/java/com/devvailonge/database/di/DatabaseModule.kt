package com.devvailonge.database.di

import android.content.Context
import androidx.room.Room
import com.devvailonge.database.SeriesDao
import com.devvailonge.database.TvShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContex: Context): TvShowDatabase =
        Room.databaseBuilder(
            appContex,
            TvShowDatabase::class.java,
            TvShowDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: TvShowDatabase): SeriesDao =
        movieDatabase.seriesDao()
}