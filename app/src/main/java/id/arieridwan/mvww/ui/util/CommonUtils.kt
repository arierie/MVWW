package id.arieridwan.mvww.ui.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by arieridwan on 20/12/18.
 */

object CommonUtils {
    const val CATEGORY_POPULAR = "popular"

    fun getPosterUrl(posterPath: String): String = "http://image.tmdb.org/t/p/w185$posterPath"

    fun getBackdropUrl(backdropPath: String): String = "http://image.tmdb.org/t/p/w500$backdropPath"

}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadFromUrl(context: Context, url: String) {
    GlideApp.with(context)
        .asBitmap()
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, android.arch.lifecycle.Observer {
        it?.let(observer)
    })
}