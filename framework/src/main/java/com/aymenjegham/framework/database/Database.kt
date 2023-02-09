package com.aymenjegham.framework.database


import androidx.room.RoomDatabase
import com.aymenjegham.framework.entity.MovieDetailsEntity
import com.aymenjegham.framework.entity.MovieEntity
import com.aymenjegham.framework.entity.RemoteKeys
import com.aymenjegham.framework.global.constants.DATABASE_FILE_NAME
import androidx.room.Database as RoomDatabse


const val DATABASE_NAME = DATABASE_FILE_NAME
const val DATABASE_VERSION = 1

@RoomDatabse(
    entities = [
        MovieEntity::class,
        MovieDetailsEntity::class,
        RemoteKeys::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)



abstract class Database : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun movieDetailsDao(): MovieDetailsDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}