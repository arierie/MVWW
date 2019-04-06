package id.arieridwan.mvww.domain.repository

import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Observable

/**
 * Created by arieridwan on 20/12/18.
 */

interface MoviesRepository {

    fun loadMoviesFromNetwork(category: String, page: Int): Observable<List<MovieUiModel>>
    fun loadMoviesFromDisk(category: String, page: Int): Observable<List<MovieUiModel>>

}