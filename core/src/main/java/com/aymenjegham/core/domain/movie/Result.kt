package com.aymenjegham.core.domain.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(

    @Expose
    @SerializedName("page")
    val page: Int,

    @Expose
    @SerializedName("movies")
    val movies: List<Movie>,

    @Expose
    @SerializedName("total_pages")
    val total_pages: Int,

    @Expose
    @SerializedName("total_results")
    val total_results: Int
)