package id.arieridwan.mvww.domain.interactor

import id.arieridwan.mvww.core.SingleUseCase
import id.arieridwan.mvww.gateway.entity.MovieListResponse
import id.arieridwan.mvww.domain.repository.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class MoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository)
    : SingleUseCase<MovieListResponse, MoviesUseCase.Params>() {

    override fun buildUseCaseSingle(params: Params): Single<MovieListResponse> {
        return with(params) {
            moviesRepository.loadMovies(category, page)
        }
    }

    data class Params(
        val category: String,
        val page: Int
    )

}