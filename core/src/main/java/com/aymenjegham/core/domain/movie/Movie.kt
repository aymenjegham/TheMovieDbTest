package com.aymenjegham.core.domain.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("adult")
    val adult: Boolean,

    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,

    @Expose
    @SerializedName("original_title")
    val originalTitle: String?,

    @Expose
    @SerializedName("overview")
    val overview: String,

    @Expose
    @SerializedName("popularity")
    val popularity: Double,

    @Expose
    @SerializedName("poster_path")
    val posterPath: String?,

    @Expose
    @SerializedName("release_date")
    val releaseDate: String?,

    @Expose
    @SerializedName("title")
    val title: String?,

    @Expose
    @SerializedName("video")
    val video: Boolean,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,

    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,

    var page: Int?,

    val trending: Boolean,

    ) : Serializable{

    val posterPathFull : String
    get() = "https://image.tmdb.org/t/p/w500/$posterPath"

}