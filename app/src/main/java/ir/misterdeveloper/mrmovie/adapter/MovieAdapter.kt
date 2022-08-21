package ir.misterdeveloper.mrmovie.adapter

import android.content.Context

import androidx.appcompat.widget.AppCompatImageView

import androidx.appcompat.widget.AppCompatTextView

import androidx.recyclerview.widget.RecyclerView

import android.graphics.drawable.Drawable

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
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
 * This is an adapter class responsible for showing movie data top rated, now playing and upcoming.
 * 19/8/2022
 * @author Mohammad Sadegh Mehrafzoon
 */

class MovieAdapter(context: Context, movieList: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.VideoVH>() {
    var movieList: List<Movie> = movieList
    var context: Context = context

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): VideoVH {
        val view: View = LayoutInflater.from(context).inflate(R.layout.movie_card, null)
        return VideoVH(view)
    }

    override fun onBindViewHolder(@NonNull holder: VideoVH, position: Int) {
        val movie = movieList[position]
        holder.textViewNameMovie.text = movie.title


        Glide.with(context)
            .load(IMAGE_BASE_URL + movie.thumbPath)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .apply(
                RequestOptions().placeholder(R.drawable.ic_place_holder)
                    .error(R.drawable.ic_place_holder)
            )
            .into(holder.imageMovie)


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class VideoVH(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewNameMovie: AppCompatTextView = itemView.findViewById(R.id.textViewNameMovie)
        var imageMovie: AppCompatImageView = itemView.findViewById(R.id.imageMovie)

    }

}
