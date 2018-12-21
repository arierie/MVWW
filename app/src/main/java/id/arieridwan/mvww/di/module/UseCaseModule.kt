package id.arieridwan.mvww.di.module

import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.domain.interactor.MoviesUseCase
import id.arieridwan.mvww.domain.repository.MoviesRepository
import javax.inject.Singleton

/**
 * Created by arieridwan on 20/12/18.
 */

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideMainUseCase(moviesRepository: MoviesRepository): MoviesUseCase =
        MoviesUseCase(moviesRepository)

}