package id.arieridwan.mvww.domain.repository

import id.arieridwan.mvww.presentation.entity.MovieViewParam
import io.reactivex.Single

/**
 * Created by arieridwan on 20/12/18.
 */

interface MoviesRepository {

    fun loadMovies(category: String, page: Int): Single<List<MovieViewParam>>

}