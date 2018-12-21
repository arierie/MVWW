package id.arieridwan.mvww.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.arieridwan.mvww.R
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.ui.GlideApp
import java.util.ArrayList

/**
 * Created by arieridwan on 20/12/18.
 */

class MoviesAdapter(val context: Context) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var mMovieList: MutableList<MovieViewParam>? = null
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.list_item, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            // TODO navigate to detail activity
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mMovieList?.let { movies ->
            val movie = movies[position]
            val posterPath = "http://image.tmdb.org/t/p/w185${movie.posterPath}"
            GlideApp.with(context)
                .asBitmap()
                .load(posterPath)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return mMovieList?.size ?: 0
    }

    fun setMovies(movieList: List<MovieViewParam>) {
        this.mMovieList = ArrayList()
        this.mMovieList?.addAll(movieList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView) as ImageView
    }

}