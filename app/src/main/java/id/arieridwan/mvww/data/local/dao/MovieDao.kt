package id.arieridwan.mvww.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import id.arieridwan.mvww.data.local.entity.Movie
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Update

/**
 * Created by arieridwan on 27/12/18.
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    fun getMovieByTitle(title: String): Movie

    @Query("SELECT * FROM movie WHERE id LIKE :id LIMIT 1")
    fun getMovieById(id: String): Movie

    @Insert
    fun insertMovies(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

}