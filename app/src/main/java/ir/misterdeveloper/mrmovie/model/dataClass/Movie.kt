package ir.misterdeveloper.mrmovie.model.dataClass

import com.google.gson.annotations.SerializedName

/**
 * This class will be useful when you are going to call api for getting movies
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

data class Movie(

     val title: String,

    @SerializedName("release_date")
     val releaseDate: String,

    @SerializedName("vote_average")
     val rating: Float = 0f,

    @SerializedName("poster_path")
     val thumbPath: String,

    @SerializedName("overview")
     val overview: String,

    @SerializedName("backdrop_path")
     val backdropPath: String,

    @SerializedName("runtime")
     val runTime: String,

    @SerializedName("tagline")
     val tagline: String,

    @SerializedName("homepage")
     val homepage: String,
)

