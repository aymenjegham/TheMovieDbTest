package com.aymenjegham.framework.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeys(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    val movieId: String,

    @ColumnInfo(name = "prevKey")
    val prevKey: Int?,

    @ColumnInfo(name = "currentPage")
    val currentPage: Int,

    @ColumnInfo(name = "nextKey")
    val nextKey: Int?,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)