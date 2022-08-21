package ir.misterdeveloper.mrmovie.model.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import ir.misterdeveloper.mrmovie.model.repository.MovieRepository
import ir.misterdeveloper.mrmovie.model.responeMessage.MovieResponse
import ir.misterdeveloper.mrmovie.util.API_KEY


/**
 * This is a presentation class that controls the communication between the view and the model
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */


class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getPopularMovies(apiKey: String, PageNo: Int): Single<MovieResponse> {
        return movieRepository.getPopularMovies(API_KEY, PageNo)
    }

    fun getTopRatedMovies(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return movieRepository.getTopRatedMovies(API_KEY, language, PageNo)
    }

    fun getNowPlaying(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return movieRepository.getNowPlaying(API_KEY, language, PageNo)
    }

    fun getUpcoming(apiKey: String, language: String, PageNo: Int): Single<MovieResponse> {
        return movieRepository.getUpcoming(API_KEY,language,PageNo)
    }
}


