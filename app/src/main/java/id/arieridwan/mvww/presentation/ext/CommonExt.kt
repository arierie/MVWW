package id.arieridwan.mvww.presentation.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.arieridwan.mvww.di.module.GlideApp

/**
 * Created by arieridwan on 2019-06-14.
 */

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
    this.observe(owner, androidx.lifecycle.Observer {
        it?.let(observer)
    })
}