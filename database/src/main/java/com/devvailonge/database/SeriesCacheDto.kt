package com.devvailonge.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "series")
data class SeriesCacheDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "genres")
    val genres: List<String>,

    @ColumnInfo(name = "summary")
    val summary: String,

    @ColumnInfo(name = "rating")
    val rating: Double
)

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }
}
