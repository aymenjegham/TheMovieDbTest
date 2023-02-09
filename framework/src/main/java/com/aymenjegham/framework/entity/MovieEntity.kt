package com.aymenjegham.framework.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "Movie", indices = [Index(value = ["id"], unique = true)])
data class MovieEntity(

    // it is not recommended to make all members of a class nullable ,but i have no control
    //over the response which it seems very unstable

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
    val backdrop_path: String?,

    @ColumnInfo(name = "original_language")
    @Expose
    @SerializedName("original_language")
    val original_language: String,

    @ColumnInfo(name = "original_title")
    @Expose
    @SerializedName("original_title")
    val original_title: String?,

    @ColumnInfo(name = "overview")
    @Expose
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "popularity")
    @Expose
    @SerializedName("popularity")
    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    @Expose
    @SerializedName("poster_path")
    val poster_path: String?,

    @ColumnInfo(name = "release_date")
    @Expose
    @SerializedName("release_date")
    val release_date: String?,

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "video")
    @Expose
    @SerializedName("video")
    val video: Boolean?,

    @ColumnInfo(name = "vote_average")
    @Expose
    @SerializedName("vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "vote_count")
    @Expose
    @SerializedName("vote_count")
    val vote_count: Int,

    @ColumnInfo(name = "page")
    @Expose
    @SerializedName("page")
    var page: Int?,

    @ColumnInfo(name = "trending")
    @Expose
    @SerializedName("trending")
    val trending: Boolean,

) : Serializable