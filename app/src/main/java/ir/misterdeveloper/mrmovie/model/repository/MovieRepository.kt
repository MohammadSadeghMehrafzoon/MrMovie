package ir.misterdeveloper.mrmovie.model.repository

import io.reactivex.Single
import ir.misterdeveloper.mrmovie.model.responeMessage.MovieResponse
import ir.misterdeveloper.mrmovie.network.ApiService
import ir.misterdeveloper.mrmovie.util.API_KEY
import ir.misterdeveloper.mrmovie.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */
class MovieRepository() {

    private var apiService: ApiService

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPopularMovies(apiKey: String, PageNo: Int): Single<MovieResponse> {
        return apiService.getPopularMovies(API_KEY, PageNo)
    }

    fun getTopRatedMovies(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return apiService.getTopRatedMovies(API_KEY, language, PageNo)
    }

    fun getNowPlaying(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return apiService.getNowPlaying(API_KEY, language, PageNo)
    }
    fun getUpcoming(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return apiService.getUpcoming(API_KEY,language,PageNo)
    }




}

