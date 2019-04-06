package id.arieridwan.mvww.domain.mapper

import id.arieridwan.mvww.data.disk.entity.Movie
import id.arieridwan.mvww.data.network.response.MovieResponse
import id.arieridwan.mvww.presentation.model.MovieUiModel

object MovieMapper {
    fun fromListOfResponseToListOfUi(list: List<MovieResponse>): List<MovieUiModel> {
        val movies: MutableList<MovieUiModel> = mutableListOf()
        list.forEach { movies.add(fromResponseToUi(it)) }
        return movies
    }
    private fun fromResponseToUi(item: MovieResponse): MovieUiModel {
        return MovieUiModel(item.title, item.posterPath, item.overview, item.backdropPath, item.releaseDate, item.id, item.video, item.voteAverage, item.popularity)
    }
    fun fromListOfEntityToListOfUi(list: List<Movie>): List<MovieUiModel> {
        val movies: MutableList<MovieUiModel> = mutableListOf()
        list.forEach { movies.add(fromEntityToUi(it)) }
        return movies
    }
    private fun fromEntityToUi(item: Movie): MovieUiModel {
        return MovieUiModel(item.title, item.posterPath, item.overview, item.backdropPath, item.releaseDate, item.id, item.video, item.voteAverage, item.popularity)
    }
}