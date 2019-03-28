package id.arieridwan.mvww.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.arieridwan.mvww.R
import id.arieridwan.mvww.presentation.entity.MovieUiModel
import id.arieridwan.mvww.presentation.util.CommonUtils.getPosterUrl
import id.arieridwan.mvww.presentation.util.loadFromUrl
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by arieridwan on 20/12/18.
 */

class MoviesAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val mMovieList: MutableList<MovieUiModel> = mutableListOf()
    private var mItemListener: MoviesListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.itemView.imageView.loadFromUrl(
            holder.itemView.context,
            getPosterUrl(mMovieList[position].posterPath)
        )
        holder.itemView.setOnClickListener {
            mItemListener?.onItemClick(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    fun setMovies(movieList: List<MovieUiModel>) {
        mMovieList.clear()
        mMovieList.addAll(movieList)
        notifyDataSetChanged()
    }

    fun setListener(itemListener: MoviesListener) {
        mItemListener = itemListener
    }

    interface MoviesListener {
        fun onItemClick(item: MovieUiModel)
    }

    class MoviesViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)

}