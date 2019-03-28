package id.arieridwan.mvww.data.mapper

import id.arieridwan.mvww.data.disk.entity.Movie
import id.arieridwan.mvww.presentation.entity.MovieUiModel
import id.arieridwan.mvww.data.network.entity.MovieResponse

/**
 * Created by arieridwan on 20/12/18.
 */

object MovieMapper {

    fun toMovieViewParamsFromResponse(movieList: List<MovieResponse>): List<MovieUiModel> {
        val movieUiModels: MutableList<MovieUiModel> = mutableListOf()
        movieList.forEach { movieResponse ->
            movieUiModels.add(
                MovieUiModel(
                    movieResponse.title, movieResponse.posterPath, movieResponse.overview,
                    movieResponse.backdropPath, movieResponse.releaseDate, movieResponse.id,
                    movieResponse.video, movieResponse.voteAverage, movieResponse.popularity
                )
            )
        }
        return movieUiModels
    }

    fun toMovieViewParamsFromMovie(movies: List<Movie>): List<MovieUiModel> {
        val uiModels: MutableList<MovieUiModel> = mutableListOf()
        movies.forEach { movie ->
            uiModels.add(
                MovieUiModel(
                    movie.title, movie.posterPath, movie.overview,
                    movie.backdropPath, movie.releaseDate, movie.id,
                    movie.video, movie.voteAverage, movie.popularity
                )
            )
        }
        return uiModels
    }

    fun toMovies(uiModel: List<MovieUiModel>): List<Movie> {
        val movies: MutableList<Movie> = mutableListOf()
        uiModel.forEach { item ->
            movies.add(Movie(
                item.title, item.posterPath, item.overview,
                item.backdropPath, item.releaseDate, item.id,
                item.video, item.voteAverage, item.popularity
            ))
        }
        return movies
    }

}