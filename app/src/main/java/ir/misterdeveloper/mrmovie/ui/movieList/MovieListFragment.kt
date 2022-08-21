package ir.misterdeveloper.mrmovie.ui.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ir.misterdeveloper.mrmovie.adapter.MovieListAdapter
import ir.misterdeveloper.mrmovie.databinding.FragmentMovieListBinding
import ir.misterdeveloper.mrmovie.model.dataClass.Movie
import ir.misterdeveloper.mrmovie.model.repository.MovieRepository
import ir.misterdeveloper.mrmovie.model.responeMessage.MovieResponse
import ir.misterdeveloper.mrmovie.model.viewModel.MovieViewModel
import ir.misterdeveloper.mrmovie.util.API_KEY
import java.util.ArrayList

/**
 * This activity is responsible for showing all movies data in the list format.
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

class MovieListFragment : Fragment() {

    lateinit var movieListBinding: FragmentMovieListBinding
    val compositeDisposable = CompositeDisposable()
    val movieList: MutableList<Movie> = ArrayList()
    private var pageNo = 1
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieListBinding = FragmentMovieListBinding.inflate(layoutInflater)

        movieViewModel = MovieViewModel(MovieRepository())
        requestDataFromServer(pageNo)

        movieListBinding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                pageNo++
                movieListBinding.pbLoading.visibility = View.VISIBLE
                requestDataFromServer(pageNo)
            }
        })

        return movieListBinding.root
    }

    private fun requestDataFromServer(pageNo: Int) {
        movieViewModel.getPopularMovies(API_KEY, pageNo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<MovieResponse> {
                override fun onSubscribe(d: Disposable) {

                    compositeDisposable!!.add(d)
                }

                override fun onSuccess(movieResponse: MovieResponse) {

                    movieListBinding.pbLoading.visibility = View.GONE
                    var movies: List<Movie> = movieResponse.results
                    movieList.addAll(movies)
                    showDataRecycler()

                }

                override fun onError(e: Throwable) {

                    movieListBinding.pbLoading.visibility = View.GONE
                    Snackbar.make(
                        movieListBinding.scrollView,
                        " Please check your internet connection",
                        Snackbar.LENGTH_LONG
                    )

                }


            })
    }

    fun showDataRecycler() {
        movieListBinding.rvMovieList.adapter =
            MovieListAdapter(requireContext().applicationContext, movieList)
        movieListBinding.rvMovieList.layoutManager = GridLayoutManager(
            requireContext().applicationContext, 2,
            RecyclerView.VERTICAL,
            false
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}