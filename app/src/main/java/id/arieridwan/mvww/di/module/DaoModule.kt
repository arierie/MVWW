package id.arieridwan.mvww.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.arieridwan.mvww.presentation.BaseApplication
import id.arieridwan.mvww.data.local.dao.MovieDao
import id.arieridwan.mvww.data.local.database.MovieDB
import javax.inject.Singleton

/**
 * Created by arieridwan on 27/12/18.
 */

@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideMovieDao(movieDB: MovieDB): MovieDao = movieDB.movieDao()

    @Provides
    @Singleton
    fun provideMovieDatabase(application: BaseApplication): MovieDB {
        return Room.databaseBuilder(application.applicationContext, MovieDB::class.java, "movie_db")
            .fallbackToDestructiveMigration()
            .build()
    }

}