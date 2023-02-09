package com.aymenjegham.framework.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "MovieDetails", indices = [Index(value = ["id"], unique = true)])
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "adult")
    @Expose
    @SerializedName("adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "budget")
    @Expose
    @SerializedName("budget")
    val budget: Int,

    @ColumnInfo(name = "homepage")
    @Expose
    @SerializedName("homepage")
    val homepage: String?,

    @ColumnInfo(name = "imdb_id")
    @Expose
    @SerializedName("imdb_id")
    val imdbId: String,

    @ColumnInfo(name = "original_language")
    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_title")
    @Expose
    @SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name = "overview")
    @Expose
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    @Expose
    @SerializedName("popularity")
    val popularity: Double,


    @ColumnInfo(name = "poster_path")
    @Expose
    @SerializedName("poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    @Expose
    @SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo(name = "revenue")
    @Expose
    @SerializedName("revenue")
    val revenue: Long,

    @ColumnInfo(name = "runtime")
    @Expose
    @SerializedName("runtime")
    val runtime: Int?,

    @ColumnInfo(name = "status")
    @Expose
    @SerializedName("status")
    val status: String,

    @ColumnInfo(name = "tagline")
    @Expose
    @SerializedName("tagline")
    val tagline: String?,

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "video")
    @Expose
    @SerializedName("video")
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,
) : Serializable {

    val posterPathFull: String
        get() = "https://image.tmdb.org/t/p/w500/$posterPath"

}