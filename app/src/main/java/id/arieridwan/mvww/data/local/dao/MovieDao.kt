package id.arieridwan.mvww.data.local.dao

import android.arch.persistence.room.*
import id.arieridwan.mvww.data.local.entity.Movie
import io.reactivex.Single

/**
 * Created by arieridwan on 27/12/18.
 */

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Single<List<Movie>>

    @Query("SELECT * FROM movie WHERE title LIKE :title LIMIT 1")
    fun getMovieByTitle(title: String): Single<Movie>

    @Query("SELECT * FROM movie WHERE id LIKE :id LIMIT 1")
    fun getMovieById(id: String): Single<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movies: List<Movie>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

}