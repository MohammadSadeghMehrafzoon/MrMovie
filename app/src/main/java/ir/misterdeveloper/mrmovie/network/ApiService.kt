package ir.misterdeveloper.mrmovie.network

import io.reactivex.Single
import ir.misterdeveloper.mrmovie.model.responeMessage.MovieResponse

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is the api interface class, it will contain all the Api call references
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */


interface ApiService {

    //Get a list of the current popular movies on TMDB. This list updates daily
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") PageNo: Int
    ): Single<MovieResponse>

    //Get the top rated movies on TMDB
    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") PageNo: Int
    ): Single<MovieResponse>

    //Get a list of movies in theatres
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") PageNo: Int
    ): Single<MovieResponse>

    //Get a list of upcoming movies in theaters
    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") PageNo: Int
    ): Single<MovieResponse>


}

