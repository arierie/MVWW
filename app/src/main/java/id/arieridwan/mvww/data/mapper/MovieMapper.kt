package id.arieridwan.mvww.data.mapper

import id.arieridwan.mvww.data.local.entity.Movie
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import id.arieridwan.mvww.data.remote.entity.MovieResponse

/**
 * Created by arieridwan on 20/12/18.
 */

object MovieMapper {

    fun toMovieViewParamsFromResponse(movieList: List<MovieResponse>): List<MovieViewParam> {
        val movieViewParams: MutableList<MovieViewParam> = mutableListOf()
        movieList.forEach { movieResponse ->
            movieViewParams.add(
                MovieViewParam(
                    movieResponse.title, movieResponse.posterPath, movieResponse.overview,
                    movieResponse.backdropPath, movieResponse.releaseDate, movieResponse.id,
                    movieResponse.video, movieResponse.voteAverage, movieResponse.popularity
                )
            )
        }
        return movieViewParams
    }

    fun toMovieViewParamsFromMovie(movies: List<Movie>): List<MovieViewParam> {
        val viewParams: MutableList<MovieViewParam> = mutableListOf()
        movies.forEach { movie ->
            viewParams.add(
                MovieViewParam(
                    movie.title, movie.posterPath, movie.overview,
                    movie.backdropPath, movie.releaseDate, movie.id,
                    movie.video, movie.voteAverage, movie.popularity
                )
            )
        }
        return viewParams
    }

    fun toMovies(viewParam: List<MovieViewParam>): List<Movie> {
        val movies: MutableList<Movie> = mutableListOf()
        viewParam.forEach { item ->
            movies.add(Movie(
                item.title, item.posterPath, item.overview,
                item.backdropPath, item.releaseDate, item.id,
                item.video, item.voteAverage, item.popularity
            ))
        }
        return movies
    }

}