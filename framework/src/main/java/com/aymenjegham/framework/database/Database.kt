package com.aymenjegham.framework.database


import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aymenjegham.framework.datasource.database.converter.GenreTypeConverter
import com.aymenjegham.framework.entity.MovieEntity
import com.aymenjegham.framework.entity.RemoteKeys
import com.aymenjegham.framework.global.constants.DATABASE_FILE_NAME
import androidx.room.Database as RoomDatabse


const val DATABASE_NAME = DATABASE_FILE_NAME
const val DATABASE_VERSION = 1

@RoomDatabse(
    entities = [
        MovieEntity::class,
        RemoteKeys::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)


@TypeConverters(
    GenreTypeConverter::class,
)
abstract class Database : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}