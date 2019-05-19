package id.arieridwan.mvww.presentation.ext

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.arieridwan.mvww.core.ui.GlideApp

/**
 * Created by arieridwan on 2019-05-19.
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