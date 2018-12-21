package id.arieridwan.mvww.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.arieridwan.mvww.R
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.ui.util.CommonUtils.getPosterUrl
import id.arieridwan.mvww.ui.util.loadFromUrl
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by arieridwan on 20/12/18.
 */

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var mMovieList: MutableList<MovieViewParam>? = null
    private var mItemListener: MoviesListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        mMovieList?.let { movies ->
            holder.itemView.imageView.loadFromUrl(holder.itemView.context,
                getPosterUrl(movies[position].posterPath))
            holder.itemView.setOnClickListener {
                mItemListener?.onItemClick(movies[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return mMovieList?.size ?: 0
    }

    fun setMovies(movieList: List<MovieViewParam>) {
        this.mMovieList?.addAll(movieList)
        notifyDataSetChanged()
    }

    fun setListener(itemListener: MoviesListener) {
        mItemListener = itemListener
    }

    interface MoviesListener {
        fun onItemClick(item: MovieViewParam)
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}