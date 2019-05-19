package id.arieridwan.mvww.presentation.util

/**
 * Created by arieridwan on 20/12/18.
 */

object CommonUtil {

    fun getPosterUrl(posterPath: String): String = "http://image.tmdb.org/t/p/w185$posterPath"

    fun getBackdropUrl(backdropPath: String): String = "http://image.tmdb.org/t/p/w500$backdropPath"

}