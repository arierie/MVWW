package id.arieridwan.mvww.data.converter

import id.arieridwan.mvww.presentation.entity.MovieViewParam
import id.arieridwan.mvww.data.remote.entity.MovieResponse

/**
 * Created by arieridwan on 20/12/18.
 */

object MovieConverter {

    fun toMovieViewParam(movieResponse: MovieResponse): MovieViewParam {
        return MovieViewParam(
            movieResponse.title, movieResponse.posterPath, movieResponse.overview,
            movieResponse.backdropPath, movieResponse.releaseDate, movieResponse.id,
            movieResponse.video, movieResponse.voteAverage, movieResponse.popularity
        )
    }

}