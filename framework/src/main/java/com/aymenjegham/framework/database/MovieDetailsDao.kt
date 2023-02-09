package com.aymenjegham.framework.database

import androidx.room.*
import com.aymenjegham.framework.entity.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDetailsEntity: MovieDetailsEntity)

    @Query("SELECT * FROM MovieDetails WHERE id = :movieId ")
    fun getMovieDetails(movieId: String): MovieDetailsEntity


}