package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.domain.repository.MoviesRepository
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMainUseCase(moviesRepository: MoviesRepository):
            LoadMoviesUseCase = LoadMoviesUseCase(moviesRepository)

}