package id.arieridwan.mvww.domain.repository

import id.arieridwan.mvww.BuildConfig
import id.arieridwan.mvww.gateway.entity.MovieListResponse
import id.arieridwan.mvww.gateway.service.ApiService
import io.reactivex.Single

class MoviesRepositoryImpl(private val apiService: ApiService) : MoviesRepository {

    override fun loadMovies(category: String, page: Int): Single<MovieListResponse> {
        return apiService.getMovies(category, BuildConfig.ApiKey, page)
    }

}