package ir.misterdeveloper.mrmovie.model.responeMessage

import com.google.gson.annotations.SerializedName
import ir.misterdeveloper.mrmovie.model.dataClass.Movie

/**
 * This class will be useful when we are going to receive the api response.
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */
data class MovieResponse(

    @SerializedName("page")
     val page: Int,

    @SerializedName("results")
     val results: List<Movie>,

    @SerializedName("totalResults")
     val totalResults: Int,

    @SerializedName("totalPages")
     val totalPages: Int
)




