package ir.misterdeveloper.mrmovie.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ir.misterdeveloper.mrmovie.R

import ir.misterdeveloper.mrmovie.model.dataClass.Movie
import ir.misterdeveloper.mrmovie.util.IMAGE_BASE_URL


/**
 * This is an adapter class responsible for showing movie data.
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */


class MovieListAdapter(private val context: Context, movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    private var movieList: List<Movie> = movieList


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_card, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        val movie: Movie = movieList[position]
        holder.tvMovieTitle.setText(movie.title)
        holder.tvMovieRatings.setText(java.lang.String.valueOf(movie.rating))
        holder.tvReleaseDate.setText(movie.releaseDate)

        // loading album cover using Glide library
        Glide.with(context)
            .load(IMAGE_BASE_URL + movie.thumbPath)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.pbLoadImage.visibility = View.GONE
                    return false
                }
            })
            .apply(
                RequestOptions().placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
            )
            .into(holder.ivMovieThumb)

    }


    override fun getItemCount(): Int {
        return movieList.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMovieTitle: AppCompatTextView = itemView.findViewById(R.id.tv_movie_title)
        var tvMovieRatings: AppCompatTextView = itemView.findViewById(R.id.tv_movie_ratings)
        var tvReleaseDate: AppCompatTextView = itemView.findViewById(R.id.tv_release_date)
        var ivMovieThumb: AppCompatImageView = itemView.findViewById(R.id.iv_movie_thumb)
        var pbLoadImage: ProgressBar = itemView.findViewById(R.id.pb_load_image)

    }

}