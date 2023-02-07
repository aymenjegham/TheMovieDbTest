package com.aymenjegham.framework.database

import androidx.paging.PagingSource
import androidx.room.*
import com.aymenjegham.framework.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieEntity: MovieEntity)

    @Query("SELECT * FROM Movie order By page")
    fun getAll(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()


}