package com.aymenjegham.framework.database

import androidx.paging.PagingSource
import androidx.room.*
import com.aymenjegham.framework.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Query("SELECT * FROM Movie WHERE trending = 0 order By page ")
    fun getTopRated(): PagingSource<Int, MovieEntity>

    @Query("SELECT * FROM Movie WHERE trending = 1")
    fun getTrendy(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM Movie WHERE trending = 0")
    suspend fun deleteAllTopRated()

    @Query("DELETE FROM Movie WHERE trending = 1")
    suspend fun deleteAllTrendy()


}