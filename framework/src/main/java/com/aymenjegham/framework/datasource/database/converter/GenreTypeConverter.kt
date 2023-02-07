package com.aymenjegham.framework.datasource.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.json.JSONObject

class GenreTypeConverter {
    @TypeConverter
    fun fromGenre(genre: List<Int>): String {
        return JSONObject().apply {
            genre.toString()
        }.toString()
    }

    @TypeConverter
    fun toGenre(genre: String): List<Int> {
        return Gson().fromJson(genre, Array<Int>::class.java).toList()
    }
}