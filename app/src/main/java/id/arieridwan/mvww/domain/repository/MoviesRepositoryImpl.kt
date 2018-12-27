package id.arieridwan.mvww.domain.repository

import id.arieridwan.mvww.BuildConfig
import id.arieridwan.mvww.data.converter.MovieConverter
import id.arieridwan.mvww.data.remote.service.ApiService
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import io.reactivex.Single

class MoviesRepositoryImpl(private val apiService: ApiService) : MoviesRepository {

    override fun loadMovies(category: String, page: Int): Single<List<MovieViewParam>> {
        return apiService.getMovies(category, BuildConfig.ApiKey, page)
            .map { response ->
                val movies: MutableList<MovieViewParam> = mutableListOf()
                response.movies.forEach { item -> movies.add(MovieConverter.toMovieViewParam(item)) }
                return@map movies
            }
    }

}