package id.arieridwan.mvww.domain.repository

import id.arieridwan.mvww.data.remote.entity.MovieResponse
import io.reactivex.Observable

/**
 * Created by arieridwan on 20/12/18.
 */

interface MoviesRepository {

    fun loadMovies(category: String, page: Int): Observable<List<MovieResponse>>

}