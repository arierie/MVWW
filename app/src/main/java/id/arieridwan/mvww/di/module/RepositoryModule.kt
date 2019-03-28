package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.BuildConfig
import id.arieridwan.mvww.data.local.dao.MovieDao
import id.arieridwan.mvww.data.local.database.MovieDB
import id.arieridwan.mvww.data.remote.service.ApiService
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.data.repositoryimpl.MoviesRepositoryImpl
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(apiKey: String, apiService: ApiService, movieDao: MovieDao): MoviesRepository =
        MoviesRepositoryImpl(apiKey, apiService, movieDao)

    @Provides
    @Singleton
    fun provideApiKey(): String = BuildConfig.ApiKey
}