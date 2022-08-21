package ir.misterdeveloper.mrmovie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.misterdeveloper.mrmovie.adapter.MovieAdapter
import ir.misterdeveloper.mrmovie.databinding.FragmentHomeBinding
import ir.misterdeveloper.mrmovie.model.dataClass.Movie
import ir.misterdeveloper.mrmovie.model.repository.MovieRepository
import ir.misterdeveloper.mrmovie.model.responeMessage.MovieResponse
import ir.misterdeveloper.mrmovie.model.viewModel.MovieViewModel
import ir.misterdeveloper.mrmovie.util.API_KEY

/**
 * This activity is responsible for showing all movies data in the list format.
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

class HomeFragment : Fragment() {


    lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var movieViewModel: MovieViewModel
    val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        movieViewModel = MovieViewModel(MovieRepository())
        requestDataGetTopRatedMovies(17)
        requestDataGetNowPlaying(2)
        requestDataGetUpcoming(6)

        return fragmentHomeBinding.root
    }

    private fun requestDataGetTopRatedMovies(pageNo: Int) {
        movieViewModel.getTopRatedMovies(API_KEY, "en-US",pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieResponse> {
                override fun onSubscribe(d: Disposable) {

                    compositeDisposable!!.add(d)
                }

                override fun onSuccess(movieResponse: MovieResponse) {

                    var movies: List<Movie> = movieResponse.results
                    fragmentHomeBinding.recyclerTopRated.adapter =
                        MovieAdapter(requireContext().applicationContext, movies)
                    fragmentHomeBinding.recyclerTopRated.layoutManager = LinearLayoutManager(
                        requireContext().applicationContext,
                        RecyclerView.HORIZONTAL,
                        false
                    )

                }

                override fun onError(e: Throwable) {


                }


            })
    }

    private fun requestDataGetNowPlaying(pageNo: Int) {
        movieViewModel.getNowPlaying(API_KEY, "en-US",pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieResponse> {
                override fun onSubscribe(d: Disposable) {

                    compositeDisposable!!.add(d)
                }

                override fun onSuccess(movieResponse: MovieResponse) {

                    var movies: List<Movie> = movieResponse.results
                    fragmentHomeBinding.recyclerNowPlaying.adapter =
                        MovieAdapter(requireContext().applicationContext, movies)
                    fragmentHomeBinding.recyclerNowPlaying.layoutManager = LinearLayoutManager(
                        requireContext().applicationContext,
                        RecyclerView.HORIZONTAL,
                        false
                    )

                }

                override fun onError(e: Throwable) {


                }


            })
    }

    private fun requestDataGetUpcoming(pageNo: Int) {
        movieViewModel.getUpcoming(API_KEY,"en-US",pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieResponse> {
                override fun onSubscribe(d: Disposable) {

                    compositeDisposable!!.add(d)
                }

                override fun onSuccess(movieResponse: MovieResponse) {

                    var movies: List<Movie> = movieResponse.results
                    fragmentHomeBinding.recyclerTrending.adapter =
                        MovieAdapter(requireContext().applicationContext, movies)
                    fragmentHomeBinding.recyclerTrending.layoutManager = LinearLayoutManager(
                        requireContext().applicationContext,
                        RecyclerView.HORIZONTAL,
                        false
                    )

                }

                override fun onError(e: Throwable) {


                }


            })
    }



}